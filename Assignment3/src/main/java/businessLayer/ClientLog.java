package businessLayer;
import dataAccessLayer.CustomerDAO;
import model.Customer;

import java.sql.SQLException;
import java.util.List;

public class ClientLog {

    private CustomerDAO client = new CustomerDAO();

    public void addClient(String name , int id){
        Customer customer= new Customer(id , name);
        client.insert(customer);
    }

    public void delete(String name , int id) throws SQLException {
        client.delete(id);
    }
    public void edit(int mod,String name ,int id)throws SQLException{
        Customer customer = new Customer(id,name);
        client.update(mod,customer);
    }

    public  List<Customer> view() throws SQLException{
        return client.findAll();
    }


}
