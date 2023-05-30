package dataAccessLayer;
import model.Product;
import java.util.*;
import javax.swing.table.DefaultTableModel;

public class ProductDAO extends AbstractDAO<Product>{
    public List<Product> findAll() {
        List<Product> products = super.findAll();

        return products;
    }

    public Product findById(int id) {
        Product product = (Product) super.findById(id);

        return product;
    }

    public Product insert(Product product) {
        Product insertedProduct = (Product) super.insert(product);

        return insertedProduct;
    }

    public Product update(int id, Product product) {
        Product updatedProduct = (Product) super.update(id, product);

        return updatedProduct;
    }

    public boolean delete(int id) {
        boolean deletedProduct = super.delete(id);

        return deletedProduct;
    }

    public DefaultTableModel retriveProperties(List list) {
        return super.retriveProperties(list);
    }
}
