package Presentation;

import dataAccessLayer.ProductDAO;
import model.Product;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ProductOp {
        JButton newProduct;

        JButton editProduct;
        JButton deleteProduct;
        JButton viewProduct;
        JTable table;
        JFrame frame;
        JPanel panel;
        public ProductOp(){
            //buttons
            newProduct = new JButton("New Product");
            editProduct = new JButton("Edit Product");
            deleteProduct = new JButton("Delete Product");
            viewProduct = new JButton("View Product");
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
                ProductDAO customerDAO = new ProductDAO();
                List<Product> customers = customerDAO.findAll();

                DefaultTableModel model = customerDAO.retriveProperties(customers); //new DefaultTableModel(new Object[]{"Id", "Name", "Quantity", "Price"}, 0);

//            for (Product product : products) {
//                model.addRow(new Object[]{product.getId(), product.getName(), product.getQuantity(), product.getPrice()});
//            }

                table.setModel(model);
                view.add(table);
                view.setVisible(true);
            });

            newProduct.addActionListener(e -> {
                JFrame frameadd = new JFrame();
                frameadd.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frameadd.setSize(300,400);
                frameadd.setLayout(null);
                JLabel idLabel = new JLabel("id");
                JLabel numeLabel = new JLabel ("nume");
                JButton add = new JButton("Add");
                JTextField id = new JTextField();
                JTextField nume = new JTextField();
                JLabel cantitateLabel = new JLabel("Cantitate");
                JTextField cantitate = new JTextField();

                //setBounds
                idLabel.setBounds(10 , 30 , 100,40);
                numeLabel.setBounds(10,75 , 100, 40);
                id.setBounds(150,30,100,40);
                nume.setBounds(150,75,100,40);
                add.setBounds(75,180,100,40);
                cantitateLabel.setBounds(10, 120,100,40);
                cantitate.setBounds(150,120 , 100,40);

                add.addActionListener(e1 -> {
                    Product product = new Product();
                    ProductDAO productDAO = new ProductDAO();

                    product.setId(Integer.parseInt(id.getText()));
                    product.setNume(nume.getText());
                    product.setNumar(Integer.parseInt(cantitate.getText()));

                    if(productDAO.findById(product.getId()) != null){
                        System.out.println("Error already exists");
                        return;
                    }

                    productDAO.insert(product);
                    System.out.println("Success");
                });

                frameadd.add(idLabel);
                frameadd.add(numeLabel);
                frameadd.add(id);
                frameadd.add(nume);
                frameadd.add(add);
                frameadd.add(cantitateLabel);
                frameadd.add(cantitate);
                //actionListener



                frameadd.setVisible(true);
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
                    ProductDAO productdao = new ProductDAO();
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
               // JLabel idNouLabel = new JLabel("id nou");
                JLabel numeLabel = new JLabel("Nume");
                JButton add = new JButton("Edit");
                JTextField id = new JTextField();
               // JTextField idNou = new JTextField();
                JTextField nume = new JTextField();
                JLabel cantitateLabel = new JLabel("Cantitate");
                JTextField cantitate = new JTextField();

                //setBounds
                idLabel.setBounds(10 , 30 , 100,30);
                id.setBounds(150,30,100,30);
              //  idNouLabel.setBounds(10,65,100,30);
                numeLabel.setBounds(10,100,100,30);
                add.setBounds(75,180,100,30);
              //  idNou.setBounds(150,65,100,30);
                nume.setBounds(150,100,100,30);
                cantitateLabel.setBounds(10, 130,100,30);
                cantitate.setBounds(150,135 , 100,30);
                framedelete.add(idLabel);
                framedelete.add(id);

                add.addActionListener(e1 -> {
                    Product product = new Product();
                    ProductDAO productDAO = new ProductDAO();

                    product.setId(Integer.parseInt(id.getText()));
                    product.setNume(nume.getText());
                    product.setNumar(Integer.parseInt(cantitate.getText()));

                    if(productDAO.findById(product.getId()) == null){
                        System.out.println("Error does not exist");
                        return;
                    }

                    productDAO.update(product.getId(),product);
                    System.out.println("Success");
                });
              //  framedelete.add(idNouLabel);
              //  framedelete.add(idNou);
                framedelete.add(nume);
                framedelete.add(numeLabel);
                framedelete.add(add);
                framedelete.add(cantitateLabel);
                framedelete.add(cantitate);
                //actionListener


                framedelete.setVisible(true);
            });

            frame.setVisible(true);
        }
}


