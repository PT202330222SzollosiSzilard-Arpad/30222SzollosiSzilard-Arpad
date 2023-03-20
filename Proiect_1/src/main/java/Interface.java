import javax.swing.*;
import java.awt.*;

public class Interface {

      Interface() {
          JFrame frame = new JFrame("Frame");
          frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          frame.setSize(500, 500);
          frame.setLayout(null);
          frame.setVisible(true);
          JButton adun = new JButton("Adunare");
          JButton scad = new JButton("Scadere");
          JLabel lab1 = new JLabel("Polinom1:");
          JLabel lab2 = new JLabel("Polinom2:");
          JLabel lab3 = new JLabel("Rezultat:");
          lab1.setBounds(100,50,100,40);
          lab2.setBounds(100,100,100,40);
          lab3.setBounds(100,300,100,40);
          JTextField tf1 = new JTextField();
          JTextField tf2 = new JTextField();
          JTextField tf3 = new JTextField();
          tf1.setBounds(250, 50 , 100,40);
          tf2.setBounds(250 , 100 ,100,40);
          tf3.setBounds(250,300,100,40);
          adun.setBounds(250,200,100,40);
          scad.setBounds(100,200,100,40);
          frame.add(adun);
          frame.add(tf1);
          frame.add(tf2);
          frame.add(lab1);
          frame.add(lab2);
          frame.add(scad);
          frame.add(tf3);
          frame.add(lab3);
      }
}
