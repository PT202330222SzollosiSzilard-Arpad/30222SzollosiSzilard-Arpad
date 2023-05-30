package dataAccessLayer;

import model.Customer;

import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.logging.Logger;

public class CustomerDAO extends AbstractDAO<Customer> {
    protected static final Logger LOGGER = Logger.getLogger(CustomerDAO.class.getName());

    public Customer findById(int id) {
        Customer client = super.findById(id);

        return client;
    }

    public List<Customer> findAll() {
       List<Customer> clients = super.findAll();

       return clients;
    }

    public Customer insert(Customer client) {
        Customer insertedClient = super.insert(client);

        return insertedClient;
    }

    public Customer update(int id, Customer client) {
        Customer updatedClient = super.update(id, client);

        return updatedClient;
    }

    public boolean delete(int id) {
        boolean deletedClient = super.delete(id);

        return deletedClient;
    }

    public DefaultTableModel retriveProperties(List<Customer> clients) {
        return super.retriveProperties(clients);
    }
}
