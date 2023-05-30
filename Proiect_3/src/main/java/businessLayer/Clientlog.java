package businessLayer;

import dataAccessLayer.ClientDAO;
import model.Customer;

import java.sql.SQLException;
import java.util.List;

public class Clientlog {

    private ClientDAO client = new ClientDAO();

    public void addClient(String name , int id){
        Customer customer= new Customer(id , name);
        client.insert(customer);
    }

    public void delete(String name , int id) throws SQLException {
        client.delete(id);
    }
    public void edit(String name ,int id)throws SQLException{
        Customer customer = new Customer(id,name);
        client.update(customer);
    }

    public  List<Customer> view() throws SQLException{
        return client.view();
    }


}
