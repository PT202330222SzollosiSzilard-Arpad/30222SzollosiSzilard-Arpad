package presentation;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ViewClient extends JFrame {
    JFrame frame;
    JTable table;
    public ViewClient(){
        frame = new JFrame("Frame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,500);
        frame.setLayout(null);
        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 10, 570, 440);
        frame.add(scrollPane);
        frame.setVisible(true);
    }

}
