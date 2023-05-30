package dataAccessLayer;

import businessLayer.ConnectionFactory;
import model.Orders;
import model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductDAO {
    protected static final Logger LOGGER = Logger.getLogger(OrdersDAO.class.getName());
    private static final String insertStatementString = "INSERT INTO orders (ProductId,ProductName,ListPrice)"
            + " VALUES (?,?,?)";
    private final static String findStatementString = "SELECT * FROM orders where id = ?";

    public static Product findById(int productId) {
        Product toReturn = null;

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findStatement = null;
        ResultSet rs = null;
        try {
            findStatement = dbConnection.prepareStatement(findStatementString);
            findStatement.setLong(1, productId);
            rs = findStatement.executeQuery();
            rs.next();


            String productName = rs.getString("ProductName");
            float listPrice = rs.getFloat("ListPrice");
            toReturn = new Product(productId, productName,listPrice);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"OrdersDAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(dbConnection);
        }
        return toReturn;
    }

    public static int insert(Product student) {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement insertStatement = null;
        int insertedId = -1;
        try {
            insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setInt(1, student.getProductId());
            insertStatement.setString(2, student.getProductName());

            insertStatement.setFloat(3,student.getProductPrice());
            insertStatement.executeUpdate();

            ResultSet rs = insertStatement.getGeneratedKeys();
            if (rs.next()) {
                insertedId = rs.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ProductDAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(dbConnection);
        }
        return insertedId;
    }

    public void update(Product prod) throws SQLException{
        Connection dbConnection = ConnectionFactory.getConnection();
        String querry = "update products set name = ? ,price = ? where ProductId = ?";
        PreparedStatement insertStatement = dbConnection.prepareStatement(querry);
        try {
            insertStatement.setInt(3, prod.getProductId());
            insertStatement.setString(1, prod.getProductName());
            insertStatement.setFloat(2,prod.getProductPrice());
            insertStatement.executeUpdate();
        }catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ProductDAO:update " + e.getMessage());
        } finally {
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(dbConnection);
        }
    }

    public void delete(int idProduct) throws SQLException{
        Connection dbConnection = ConnectionFactory.getConnection();
        String querry = "delete from products where ProductId = ?";
        PreparedStatement insertStatement = dbConnection.prepareStatement(querry);
        try {
            insertStatement.setInt(1, idProduct);
            insertStatement.executeUpdate();
        }catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ProductDAO:delete " + e.getMessage());
        } finally {
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(dbConnection);
        }
    }
    public List<Product> view() throws SQLException{
        Connection dbConnection = ConnectionFactory.getConnection();
        String querry = "select * from product";
        List<Product> order = new ArrayList<>();
        PreparedStatement statement = dbConnection.prepareStatement(querry);
        try{
            ResultSet result = statement.executeQuery();
            while(result.next()) {
                Product orderz = new Product(result.getInt("ProductId"),result.getString("ProductName"),result.getInt("ListPrice"));
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
