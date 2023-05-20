package dataAccessLayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import businessLayer.ConnectionFactory;
import model.Orders;

public class OrdersDAO {
    protected static final Logger LOGGER = Logger.getLogger(OrdersDAO.class.getName());
    private static final String insertStatementString = "INSERT INTO student (name,address,email,age)"
            + " VALUES (?,?,?,?)";
    private final static String findStatementString = "SELECT * FROM student where id = ?";

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

            String name = rs.getString("name");
            String address = rs.getString("customerId");
            int clientId = rs.getInt("clientId");
            toReturn = new Orders(clientId, orderId, address, email, age);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"StudentDAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(dbConnection);
        }
        return toReturn;
    }

    public static int insert(Student student) {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement insertStatement = null;
        int insertedId = -1;
        try {
            insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setString(1, student.getName());
            insertStatement.setString(2, student.getAddress());
            insertStatement.setString(3, student.getEmail());
            insertStatement.setInt(4, student.getAge());
            insertStatement.executeUpdate();

            ResultSet rs = insertStatement.getGeneratedKeys();
            if (rs.next()) {
                insertedId = rs.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "StudentDAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(dbConnection);
        }
        return insertedId;
    }


}
