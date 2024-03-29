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
import model.Product;


public class ClientDAO {

    protected static final Logger LOGGER = Logger.getLogger(ClientDAO.class.getName());
    private static final String insertStatementString = "INSERT INTO customer (customername)"
            + " VALUES (?)";
    private final static String findStatementString = "SELECT * FROM customer where customerid = ?";

    public static Customer findById(int customerId) {
        Customer toReturn = null;

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findStatement = null;
        ResultSet rs = null;
        try {
            findStatement = dbConnection.prepareStatement(findStatementString);
            findStatement.setLong(1, customerId);
            rs = findStatement.executeQuery();
            rs.next();

            String name = rs.getString("name");
            toReturn = new Customer(customerId, name);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"CustomerDAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(dbConnection);
        }
        return toReturn;
    }

    public void insert(Customer customer) {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement insertStatement = null;
        int insertedId = -1;
        try {
            insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setString(1, customer.getName());
            insertStatement.executeUpdate();

            ResultSet rs = insertStatement.getGeneratedKeys();
            if (rs.next()) {
                insertedId = rs.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "CustomerDAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(dbConnection);
        }

    }
    public void update(Customer prod) throws SQLException{
        Connection dbConnection = ConnectionFactory.getConnection();
        String querry = "update customer set CustomerName = ?  where CustomerId = ?";
        PreparedStatement insertStatement = dbConnection.prepareStatement(querry);
        try {
            insertStatement.setInt(2, prod.getCustomerId());
            insertStatement.setString(1, prod.getName());

            insertStatement.executeUpdate();
        }catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ProductDAO:update " + e.getMessage());
        } finally {
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(dbConnection);
        }
    }

    public void delete(int idClient) throws SQLException{
        Connection dbConnection = ConnectionFactory.getConnection();
        String querry = "delete from customer where CustomerId = ?";
        PreparedStatement insertStatement = dbConnection.prepareStatement(querry);
        try {
            insertStatement.setInt(1, idClient);
            insertStatement.executeUpdate();
        }catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ProductDAO:delete " + e.getMessage());
        } finally {
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(dbConnection);
        }
    }

    public List<Customer> view() throws SQLException{
        Connection dbConnection = ConnectionFactory.getConnection();
        String querry = "select * from customer";
        List<Customer> clienti = new ArrayList<>();
        PreparedStatement statement = dbConnection.prepareStatement(querry);
        try{
            ResultSet result = statement.executeQuery();
            while(result.next()) {
                Customer customer = new Customer(result.getInt("CustomerId"), result.getString("CustomerName"));
                clienti.add(customer);
            }
        }catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ProductDAO:view " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(dbConnection);
        }
        return clienti;
    }

}
