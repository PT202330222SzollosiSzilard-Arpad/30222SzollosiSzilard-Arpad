package presentation;

import javax.swing.*;

public class ClientOp extends JFrame {
    JButton addClient;
    JButton deleteClient;
    JButton editClient;
    JButton viewClient;

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

        //actionListener


        //add

        frame.add(addClient);
        frame.add(deleteClient);
        frame.add(editClient);
        frame.add(viewClient);

        frame.setVisible(true);
    }
}
