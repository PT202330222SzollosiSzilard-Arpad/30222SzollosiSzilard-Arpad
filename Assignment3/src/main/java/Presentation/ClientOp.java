package Presentation;

import businessLayer.ClientLog;
import dataAccessLayer.CustomerDAO;
import model.Customer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientOp extends JFrame {
    JButton addClient;
    JButton deleteClient;
    JButton editClient;
    JButton viewClient;

    DefaultTableModel defaulttablemodel;

    JTable table;
    List<Customer> list;
    JFrame frame;
    public ClientOp(){
        //buttons

        addClient = new JButton("Add Client");
        deleteClient = new JButton("Delete Client");
        editClient = new JButton("Edit Client");
        viewClient = new JButton("View Client");
        JPanel panel = new JPanel();

        //frame
        frame = new JFrame("Frame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,500);
        frame.setLayout(null);

        //bounds
        addClient.setBounds(30 ,30,150,30);
        deleteClient.setBounds(200 , 30, 150,30);
        editClient.setBounds(30,70,150,30);
        viewClient.setBounds(200,70,150,30);

        viewClient.addActionListener(e -> {
            JFrame view = new JFrame();
            view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            view.setSize(600,500);
            view.setContentPane(panel);
            table = new JTable();
            frame.setLayout(null);
            CustomerDAO customerDAO = new CustomerDAO();
            List<Customer> customers = customerDAO.findAll();

            DefaultTableModel model = customerDAO.retriveProperties(customers); //new DefaultTableModel(new Object[]{"Id", "Name", "Quantity", "Price"}, 0);

//            for (Product product : products) {
//                model.addRow(new Object[]{product.getId(), product.getName(), product.getQuantity(), product.getPrice()});
//            }

            table.setModel(model);
            view.add(table);
            view.setVisible(true);
        });

        addClient.addActionListener(e -> {
            JFrame frameadd = new JFrame();
            frameadd.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frameadd.setSize(300,400);
            frameadd.setLayout(null);
            JLabel idLabel = new JLabel("id");
            JLabel numeLabel = new JLabel ("nume");
            JButton add = new JButton("Add");
            JTextField id = new JTextField();
            JTextField nume = new JTextField();

            //setBounds
            idLabel.setBounds(10 , 30 , 100,50);
            numeLabel.setBounds(10,90 , 100, 50);
            id.setBounds(150,30,100,50);
            nume.setBounds(150,90,100,50);
            add.setBounds(75,180,100,50);


            //actionListener

            add.addActionListener(e1 -> {
                Customer customer = new Customer();
                CustomerDAO customerdao = new CustomerDAO();
                customer.setId(Integer.parseInt(id.getText()));
                customer.setNume(nume.getText());

                if(customerdao.findById(Integer.parseInt(id.getText())) != null){
                    System.out.println("ERORR Already exists");
                    return ;
                }
                customerdao.insert(customer);
                System.out.println("Success");
            });
            frameadd.add(idLabel);
            frameadd.add(numeLabel);
            frameadd.add(id);
            frameadd.add(nume);
            frameadd.add(add);
            //actionListener



            frameadd.setVisible(true);
        });

        deleteClient.addActionListener(e -> {
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
                Customer customer = new Customer();
                CustomerDAO customerdao = new CustomerDAO();
                customer.setId(Integer.parseInt(id.getText()));


                if(customerdao.findById(Integer.parseInt(id.getText())) == null){
                    System.out.println("ERORR does not exists");
                    return ;
                }
                customerdao.delete(customer.getId());
                System.out.println("Success");
            });
            framedelete.add(idLabel);
            framedelete.add(id);
            framedelete.add(add);
            //actionListener

            framedelete.setVisible(true);
        });
        //actionListener
        editClient.addActionListener(e -> {
            JFrame framedelete = new JFrame();
            framedelete.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            framedelete.setSize(300,400);
            framedelete.setLayout(null);
            JLabel idLabel = new JLabel("id");
           // JLabel idNouLabel = new JLabel("id nou");
            JLabel numeLabel = new JLabel("Nume");
            JButton add = new JButton("Edit");
            JTextField id = new JTextField();
            JTextField idNou = new JTextField();
            JTextField nume = new JTextField();

            add.addActionListener(e1 -> {
                Customer customer = new Customer();
                CustomerDAO customerdao = new CustomerDAO();
                customer.setId(Integer.parseInt(id.getText()));
                customer.setNume(nume.getText());

                if(customerdao.findById(Integer.parseInt(id.getText())) == null){
                    System.out.println("ERORR does not exist");
                    return ;
                }
                customerdao.update(Integer.parseInt(id.getText()),customer);
                System.out.println("Success");
            });
            //setBounds
            idLabel.setBounds(10 , 30 , 100,30);
            id.setBounds(150,30,100,30);
           // idNouLabel.setBounds(10,65,100,30);
            numeLabel.setBounds(10,100,100,30);
            add.setBounds(75,180,100,30);
            idNou.setBounds(150,65,100,30);
            nume.setBounds(150,100,100,30);
            framedelete.add(idLabel);
            framedelete.add(id);
           // framedelete.add(idNouLabel);
            //framedelete.add(idNou);
            framedelete.add(nume);
            framedelete.add(numeLabel);
            framedelete.add(add);
            //actionListener

            framedelete.setVisible(true);
        });

        //add

        frame.add(addClient);
        frame.add(deleteClient);
        frame.add(editClient);
        frame.add(viewClient);

        frame.setVisible(true);
    }


    private void refresh(){
        defaulttablemodel.setRowCount(0);

        defaulttablemodel.fireTableDataChanged();
    }

}
