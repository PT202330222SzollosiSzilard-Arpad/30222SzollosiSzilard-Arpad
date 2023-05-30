package businessLayer;

import dataAccessLayer.ClientDAO;

import dataAccessLayer.OrdersDAO;
import model.Orders;

import java.sql.SQLException;
import java.util.List;

public class OrdersLog {
    private OrdersDAO order;

    public void addOrder(int x , int y , String z ,int r, float a){
        Orders customer= new Orders(x ,y ,z ,r,a);
        order.insert(customer);
    }

    public void delete(int id) throws SQLException {
        order.delete(id);
    }
    public void edit(int x , int y , String z ,int r, float a)throws SQLException{
        Orders customer = new Orders(x ,y ,z ,r,a);
        order.update(customer);
    }

    public List<Orders> viewO() throws SQLException{
        return order.view();
    }

}
