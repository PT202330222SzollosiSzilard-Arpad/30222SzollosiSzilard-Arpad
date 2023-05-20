package presentation;

import javax.swing.*;

public class ProductOrders {

    JLabel idProd;
    JLabel clientId;
    JLabel amount;
    JTextField idProdIn;
    JTextField clientIdIn;
    JTextField amountIn;

    JFrame frame;
    public ProductOrders(){
        idProd = new JLabel("Product Id");
        clientId = new JLabel("Client Id");
        amount = new JLabel("Amount");
        idProdIn = new JTextField();
        clientIdIn = new JTextField();
        amountIn = new JTextField();
        //frame
        frame = new JFrame("Frame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,500);
        frame.setLayout(null);
        //bounds;
        idProd.setBounds(20 , 40 , 150,30);
        clientId.setBounds(20 , 80,150,30);
        amount.setBounds(20,120,150,30);
        idProdIn.setBounds(200,40,50,30);
        clientIdIn.setBounds(200,80,50,30);
        amountIn.setBounds(200,120,50,30);
        //actionListener



        //add
        frame.add(idProd);
        frame.add(idProdIn);
        frame.add(clientId);
        frame.add(clientIdIn);
        frame.add(amount);
        frame.add(amountIn);

        frame.setVisible(true);
    }
}
