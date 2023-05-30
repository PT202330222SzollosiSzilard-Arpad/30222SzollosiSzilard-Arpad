package businessLayer;
import dataAccessLayer.CustomerDAO;

import dataAccessLayer.OrdersDAO;
import model.Orders;

import java.sql.SQLException;
import java.util.List;
public class OrdersLog {
    private OrdersDAO order;

    public void addOrder(int id,int x , int y , int z){
        Orders customer= new Orders(id,x ,y ,z);
        order.insert(customer);
    }

    public void delete(int id) throws SQLException {
        order.delete(id);
    }
    public void edit(int id,int x , int y , int z)throws SQLException{
        Orders orderz = new Orders(id,x ,y ,z);
        order.update(id,orderz);
    }

    public List<Orders> viewO() throws SQLException{
        return order.findAll();
    }
}
