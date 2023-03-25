import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Integer.parseInt;

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
          tf1.setBounds(250, 50 , 400,40);
          tf2.setBounds(250 , 100 ,400,40);
          tf3.setBounds(250,300,400,40);
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

          adun.addActionListener(new ActionListener() {

              @Override
              public void actionPerformed(ActionEvent e) {
                  Polinom p1 = new Polinom();
                  Polinom p2 = new Polinom();
                  String rezultat = new String();
                  String polin1 = tf1.getText();
                  String polin2 = tf2.getText();
                  String pattern1 = "(\\+?\\-?\\d{1,9}x\\^\\-?\\d{1,9})";
                  String pattern2 = "(\\-?\\d{1,9})";
                  Pattern patt1 = Pattern.compile(pattern1);
                  Pattern patt2 = Pattern.compile(pattern2);
                  Matcher match1 = patt1.matcher(polin1);
                  while(match1.find()){

                      Matcher match2 = patt2.matcher(match1.group(1));
                      System.out.println(match1.group(1));
                      Integer coef=0;
                      Integer exp = 0;
                      while(match2.find()){
                        if(coef == 0){
                            coef = parseInt(match2.group(1));
                        }
                        else {
                            exp = parseInt(match2.group(1));
                        }

                      }
                      Monom m = new Monom(coef,exp);
                      p1.add(exp,m);
                  }
                  Matcher match3 = patt1.matcher(polin2);
                  while(match3.find()){

                      Matcher match2 = patt2.matcher(match3.group(1));
                      System.out.println(match3.group(1));
                      Integer coef=0;
                      Integer exp = 0;
                      while(match2.find()){
                          if(coef == 0){
                              coef = parseInt(match2.group(1));
                          }
                          else {
                              exp = parseInt(match2.group(1));
                          }

                      }
                      Monom m = new Monom(coef,exp);
                      p2.add(exp,m);
                  }
                 Operatii op = new Operatii();

                 String rez_str = op.adunare(p1,p2);

                 tf3.setText(rez_str);


              }
          });


      }

}
