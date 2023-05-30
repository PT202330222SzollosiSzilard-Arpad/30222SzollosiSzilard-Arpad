package dataAccessLayer;
import model.Orders;

import java.util.*;
import java.util.logging.Logger;

public class OrdersDAO extends AbstractDAO<Orders>{
    protected static final Logger LOGGER = Logger.getLogger(OrdersDAO.class.getName());

    public List<Orders> findAll() {
        List<Orders> orders = super.findAll();

        return orders;
    }
    public Orders findById(int id) {
        Orders order = (Orders) super.findById(id);

        return order;
    }

    public Orders insert(Orders order) {
        Orders insertedOrder = (Orders) super.insert(order);

        return insertedOrder;
    }

    public Orders update(int id, Orders order) {
        Orders updatedOrder = (Orders) super.update(id, order);

        return updatedOrder;
    }

    public boolean delete(int id) {
        boolean deletedOrder = super.delete(id);

        return deletedOrder;
    }
}
