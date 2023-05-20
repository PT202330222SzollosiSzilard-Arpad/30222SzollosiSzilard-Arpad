package presentation;

import javax.swing.*;

public class ProductOp {
    JButton newProduct;

    JButton editProduct;
    JButton deleteProduct;
    JButton viewProduct;
    JFrame frame;
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


        frame.setVisible(true);
    }
}
