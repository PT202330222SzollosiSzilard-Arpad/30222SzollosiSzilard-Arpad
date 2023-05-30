package dataAccessLayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import businessLayer.ConnectionFactory;
import model.Customer;
import model.Orders;
import model.Product;

public class OrdersDAO {
    protected static final Logger LOGGER = Logger.getLogger(OrdersDAO.class.getName());
    private static final String insertStatementString = "INSERT INTO orders (OrderId,OrderDate,CustomerId,EmployeeId,Total)"
            + " VALUES (?,?,?,?,?)";
    private final static String findStatementString = "SELECT * FROM orders where id = ?";

    public static Orders findById(int orderId) {
        Orders toReturn = null;

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findStatement = null;
        ResultSet rs = null;
        try {
            findStatement = dbConnection.prepareStatement(findStatementString);
            findStatement.setLong(1, orderId);
            rs = findStatement.executeQuery();
            rs.next();

            int customerId = rs.getInt("CustomerId");
            String date = rs.getString("OrderDate");
            int employeeId = rs.getInt("clientId");
            float total = rs.getFloat("Total");
            toReturn = new Orders(customerId, orderId, date, employeeId, total);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"OrdersDAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(dbConnection);
        }
        return toReturn;
    }

    public static int insert(Orders student) {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement insertStatement = null;
        int insertedId = -1;
        try {
            insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setInt(1, student.getOrder_id());
            insertStatement.setString(2, student.getOrder_date());
            insertStatement.setInt(3, student.getCustomer_id());
            insertStatement.setInt(4, student.getEmployeeId());
            insertStatement.setFloat(5,student.getTotal());
            insertStatement.executeUpdate();

            ResultSet rs = insertStatement.getGeneratedKeys();
            if (rs.next()) {
                insertedId = rs.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "OrdersDAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(dbConnection);
        }
        return insertedId;
    }

    public void update(Orders order) throws SQLException{
        Connection dbConnection = ConnectionFactory.getConnection();
        String querry = "update Orders set customer_id = ?,employee_id = ? , total = ? , where order_id= ?";
        PreparedStatement insertStatement = dbConnection.prepareStatement(querry);
        try {
            insertStatement.setInt(4, order.getOrder_id());

            insertStatement.setInt(1, order.getCustomer_id());
            insertStatement.setInt(2, order.getEmployeeId());
            insertStatement.setFloat(3,order.getTotal());
            insertStatement.executeUpdate();
        }catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ProductDAO:update " + e.getMessage());
        } finally {
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(dbConnection);
        }
    }

    public void delete(int id) throws SQLException{
        Connection dbConnection = ConnectionFactory.getConnection();
        String querry = "delete from Orders where orders_id= ?";
        PreparedStatement insertStatement = dbConnection.prepareStatement(querry);
        try {
            insertStatement.setInt(1, id);
            insertStatement.executeUpdate();
        }catch (SQLException e) {
            LOGGER.log(Level.WARNING, "OrdersDAO:delete " + e.getMessage());
        } finally {
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(dbConnection);
        }
    }

    public List<Orders> view() throws SQLException{
        Connection dbConnection = ConnectionFactory.getConnection();
        String querry = "select * from orders";
        List<Orders> order = new ArrayList<>();
        PreparedStatement statement = dbConnection.prepareStatement(querry);
        try{
            ResultSet result = statement.executeQuery();
            while(result.next()) {
                Orders orderz = new Orders(result.getInt("CustomerId"), result.getInt("CustomerId") ,result.getString("OrderDate"), result.getInt("EmployeeId"), result.getFloat("total") );
                order.add(orderz);
            }
        }catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ProductDAO:view " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(dbConnection);
        }
        return order;
    }

}
