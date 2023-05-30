package businessLayer;

import Presentation.FirstPage;
import dataAccessLayer.OrdersDAO;
import dataAccessLayer.ProductDAO;
import dataAccessLayer.CustomerDAO;
import model.Customer;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.SQLException;



public class main {
    public static void main(String[] args) {




        CustomerDAO clientDAO = new CustomerDAO();
        OrdersDAO orderDAO = new OrdersDAO();
        ProductDAO productDAO = new ProductDAO();

        new FirstPage();
    }
}
