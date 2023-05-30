package businessLayer;

import dataAccessLayer.ProductDAO;
import model.Product;

import java.sql.SQLException;
import java.util.List;

public class ProductLog {
    private ProductDAO client;

    public void addClient(int x , String n, float pr){
        Product customer= new Product(x,n,pr);
        client.insert(customer);
    }

    public void delete(int x , String n, float pr) throws SQLException {
        client.delete(x);
    }
    public void edit(int x , String n, float pr)throws SQLException{
        Product customer = new Product(x,n,pr);
        client.update(customer);
    }

    public List<Product> view() throws SQLException{
        return client.view();
    }

}
