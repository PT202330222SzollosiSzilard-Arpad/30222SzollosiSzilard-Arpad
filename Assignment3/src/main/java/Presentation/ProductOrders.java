package Presentation;

import dataAccessLayer.OrdersDAO;
import dataAccessLayer.ProductDAO;
import model.Orders;
import model.Product;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ProductOrders {

    JButton newProduct;

    JButton editProduct;
    JButton deleteProduct;
    JButton viewProduct;
    JTable table;
    JFrame frame;
    JPanel panel;
    public ProductOrders(){
        //buttons
        newProduct = new JButton("New Order");
        editProduct = new JButton("Edit Order");
        deleteProduct = new JButton("Delete Order");
        viewProduct = new JButton("View Order");
        //frame;
        frame = new JFrame("Frame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,500);
        frame.setLayout(null);
        //bounds
        newProduct.setBounds(30 ,30,150,30);
        editProduct.setBounds(200 , 30, 150,30);
        deleteProduct.setBounds(30,70,150,30);
        viewProduct.setBounds(200,70,150,30);
        //actionListeners

        //add
        frame.add(newProduct);
        frame.add(editProduct);
        frame.add(deleteProduct);
        frame.add(viewProduct);
        panel = new JPanel();

        viewProduct.addActionListener(e -> {
            JFrame view = new JFrame();
            view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            view.setSize(600,500);
            view.setContentPane(panel);
            table = new JTable();
            frame.setLayout(null);
            OrdersDAO customerDAO = new OrdersDAO();
            List<Orders> customers = customerDAO.findAll();

            DefaultTableModel model = customerDAO.retriveProperties(customers); //new DefaultTableModel(new Object[]{"Id", "Name", "Quantity", "Price"}, 0);

//            for (Product product : products) {
//                model.addRow(new Object[]{product.getId(), product.getName(), product.getQuantity(), product.getPrice()});
//            }

            table.setModel(model);
            view.add(table);
            view.setVisible(true);
        });

        newProduct.addActionListener(e -> {
            JFrame framedelete = new JFrame();
            framedelete.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            framedelete.setSize(300,400);
            framedelete.setLayout(null);
            JLabel idLabel = new JLabel("id");
            JLabel idNouLabel = new JLabel("id product");
            JLabel idProductLabel = new JLabel("id customer");
            JButton add = new JButton("Insert");
            JTextField id = new JTextField();
            JTextField idNou = new JTextField();
            JTextField idCustomer = new JTextField();
            JLabel cantitateLabel = new JLabel("Cantitate");
            JTextField cantitate = new JTextField();

            //setBounds
            idLabel.setBounds(10 , 30 , 100,30);
            id.setBounds(150,30,100,30);
            idNouLabel.setBounds(10,65,100,30);
            idProductLabel.setBounds(10,100,100,30);
            add.setBounds(75,180,100,30);
            idNou.setBounds(150,65,100,30);
            idCustomer.setBounds(150,100,100,30);
            cantitateLabel.setBounds(10, 130,100,30);
            cantitate.setBounds(150,135 , 100,30);
            framedelete.add(idLabel);
            framedelete.add(id);

            add.addActionListener(e1 -> {
                Product product = new Product();
                ProductDAO productDAO = new ProductDAO();

                Orders order = new Orders();
                OrdersDAO ordersDAO = new OrdersDAO();
                order.setId(Integer.parseInt(id.getText()));
                order.setIdproduct(Integer.parseInt(idNou.getText()));
                order.setIdcustomer(Integer.parseInt(idCustomer.getText()));
                order.setNumar(Integer.parseInt(cantitate.getText()));

                if(ordersDAO.findById(order.getId()) != null){
                    System.out.println("Already exists!!");
                    return;
                }
                if(productDAO.findById((order.getIdproduct())).getNumar() < order.getNumar() )
                {
                    System.out.println("Error Cantitate prea mare !!");
                    return;
                }

                ordersDAO.insert(order);
                System.out.println("Success");
            });
            framedelete.add(idNouLabel);
            framedelete.add(idNou);
            framedelete.add(idCustomer);
            framedelete.add(idProductLabel);
            framedelete.add(add);
            framedelete.add(cantitateLabel);
            framedelete.add(cantitate);
            //actionListener


            framedelete.setVisible(true);
        });

        deleteProduct.addActionListener(e -> {
            JFrame framedelete = new JFrame();
            framedelete.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            framedelete.setSize(300,400);
            framedelete.setLayout(null);
            JLabel idLabel = new JLabel("id");
            JButton add = new JButton("Delete");
            JTextField id = new JTextField();

            //setBounds
            idLabel.setBounds(10 , 30 , 100,50);
            id.setBounds(150,30,100,50);
            add.setBounds(75,180,100,50);
            add.addActionListener(e1 -> {
                OrdersDAO productdao = new OrdersDAO();
                if(productdao.findById(Integer.parseInt(id.getText())) == null){
                    System.out.println("Does not exist");
                    return;
                }
                productdao.delete(Integer.parseInt(id.getText()));
                System.out.println("Success");
            });
            framedelete.add(idLabel);
            framedelete.add(id);
            framedelete.add(add);
            //actionListener

            framedelete.setVisible(true);
        });
        //actionListener
        editProduct.addActionListener(e -> {
            JFrame framedelete = new JFrame();
            framedelete.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            framedelete.setSize(300,400);
            framedelete.setLayout(null);
            JLabel idLabel = new JLabel("id");
             JLabel idNouLabel = new JLabel("id product");
            JLabel idProductLabel = new JLabel("id customer");
            JButton add = new JButton("Edit");
            JTextField id = new JTextField();
             JTextField idNou = new JTextField();
            JTextField idCustomer = new JTextField();
            JLabel cantitateLabel = new JLabel("Cantitate");
            JTextField cantitate = new JTextField();

            //setBounds
            idLabel.setBounds(10 , 30 , 100,30);
            id.setBounds(150,30,100,30);
            idNouLabel.setBounds(10,65,100,30);
            idProductLabel.setBounds(10,100,100,30);
            add.setBounds(75,180,100,30);
            idNou.setBounds(150,65,100,30);
            idCustomer.setBounds(150,100,100,30);
            cantitateLabel.setBounds(10, 130,100,30);
            cantitate.setBounds(150,135 , 100,30);
            framedelete.add(idLabel);
            framedelete.add(id);

            add.addActionListener(e1 -> {
                Product product = new Product();
                ProductDAO productDAO = new ProductDAO();

                Orders order = new Orders();
                OrdersDAO ordersDAO = new OrdersDAO();
                order.setId(Integer.parseInt(id.getText()));
                order.setIdproduct(Integer.parseInt(idNou.getText()));
                order.setIdcustomer(Integer.parseInt(idCustomer.getText()));
                order.setNumar(Integer.parseInt(cantitate.getText()));

                if(ordersDAO.findById(order.getId()) == null){
                    System.out.println("Error does not exist");
                    return;
                }
                if(productDAO.findById((order.getIdproduct())).getNumar() < order.getNumar() )
                {
                    System.out.println("Error Cantitate prea mare !!");
                    return;
                }

                ordersDAO.update(order.getId(),order);
                System.out.println("Success");
            });
              framedelete.add(idNouLabel);
             framedelete.add(idNou);
            framedelete.add(idCustomer);
            framedelete.add(idProductLabel);
            framedelete.add(add);
            framedelete.add(cantitateLabel);
            framedelete.add(cantitate);
            //actionListener


            framedelete.setVisible(true);
        });

        frame.setVisible(true);
    }
}
