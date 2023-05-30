package Presentation;

import javax.swing.*;
import java.awt.event.*;

public class FirstPage extends JFrame implements ActionListener{

    JButton clientOp;
    JButton productOp;
    JButton productOrders;

    JFrame frame;
    public FirstPage(){
        clientOp = new JButton("Client Operations");
        productOp = new JButton("Product Operations");
        productOrders = new JButton("Product Orders");

        //frame
        frame = new JFrame("Frame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,500);
        frame.setLayout(null);

        //bounds
        clientOp.setBounds(30 ,70 ,200,30);
        productOp.setBounds(300,70,200,30);
        productOrders.setBounds(175,140,200,30);

        //actionListener
        clientOp.addActionListener(this::actionPerformed);
        productOp.addActionListener(e -> {
            frame.setVisible(false);
            new ProductOp();
        });

        productOrders.addActionListener(e -> {
            frame.setVisible(false);
            new ProductOrders();
        });
        //add

        frame.add(clientOp);
        frame.add(productOp);
        frame.add(productOrders);

        frame.setVisible(true);

    }

    public void actionPerformed(ActionEvent e){
        frame.setVisible(false);
        new ClientOp();
    }

}
