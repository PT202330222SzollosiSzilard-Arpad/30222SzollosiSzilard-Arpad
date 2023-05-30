package presentation;

import businessLayer.Clientlog;
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
    List<Customer> list;
    JFrame frame;
    public ClientOp(){
        //buttons

        addClient = new JButton("Add Client");
        deleteClient = new JButton("Delete Client");
        editClient = new JButton("Edit Client");
        viewClient = new JButton("View Client");

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
            frame.setVisible(false);
            JFrame viewFrame = new JFrame();
            viewFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            viewFrame.setSize(600,500);
            viewFrame.setLayout(null);
            viewFrame.setVisible(false);
            Clientlog clientz = new Clientlog();
            try {
                list = clientz.view();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

            defaulttablemodel = Utils.getelements(list);
            JTable tab = new JTable(defaulttablemodel);

            viewFrame.add(tab);
            viewFrame.pack();
            viewFrame.setVisible(true);
            refresh();
        });

        //actionListener


        //add

        frame.add(addClient);
        frame.add(deleteClient);
        frame.add(editClient);
        frame.add(viewClient);

        frame.setVisible(true);
    }


    private void refresh(){
        defaulttablemodel.setRowCount(0);
        for(Customer customer : list){
            defaulttablemodel.addRow(new Object[]{customer.getCustomerId(),customer.getName()});
        }
        defaulttablemodel.fireTableDataChanged();
    }

}
