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
          JTextField tf1 = new JTextField();
          adun.setBounds(200,200,70,50);
          frame.add(adun);
      }
}
