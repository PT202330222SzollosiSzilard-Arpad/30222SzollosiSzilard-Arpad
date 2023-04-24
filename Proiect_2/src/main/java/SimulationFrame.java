import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.List;


public class SimulationFrame implements ActionListener{
    JTextField nClients;
    JTextField tLim;
    JTextField minArrT;
    JTextField maxArrT;
    JTextField minSerT;
    JTextField maxSert;

    JTextField queue;
    ArrayList<Integer> list=new ArrayList<>();
    JFrame frame;
    JTextArea textArea;
    JButton start;
    boolean started = false;
    public SimulationFrame(){

        frame = new JFrame("Frame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLayout(null);

        JLabel lab1 = new JLabel("N:");
        JLabel lab2 = new JLabel("Time Limit :");
        JLabel lab3 = new JLabel("Minimum Arrival:");
        JLabel lab4 = new JLabel("Maximum Arrival:");
        JLabel lab5 = new JLabel("Min Service time:");
        JLabel lab6 = new JLabel("Naximum ServiceTime:");
        JLabel lab7 = new JLabel("Queues :");
        nClients = new JTextField();
        tLim = new JTextField();
        minArrT = new JTextField();
        maxArrT = new JTextField();
        minSerT = new JTextField();
        maxSert = new JTextField();
        queue = new JTextField();
        start = new JButton("Start");
        start.addActionListener(this);




        lab1.setBounds(10 ,10 ,100 ,20);
        lab2.setBounds(10 ,35 ,100 ,20);
        lab3.setBounds(10 ,70 ,100 ,20);
        lab4.setBounds(10 ,105 ,100 ,20);
        lab5.setBounds(10 ,140 ,100 ,20);
        lab6.setBounds(10 ,175 ,150 ,20);
        lab7.setBounds(10 ,210 ,100 ,20);
        nClients.setBounds(200,10,150,20);
        tLim.setBounds(200,35,150,20);
        minArrT.setBounds(200,70,150,20);
        maxArrT.setBounds(200,105,150,20);
        minSerT.setBounds(200,140,150,20);
        maxSert.setBounds(200,175,150,20);
        queue.setBounds(200,210,150,20);
        start.setBounds(170,260 ,70,20);


        frame.add(lab1);
        frame.add(lab2);
        frame.add(lab3);
        frame.add(lab4);
        frame.add(lab5);
        frame.add(lab6);
        frame.add(lab7);
        frame.add(nClients);
        frame.add(tLim);
        frame.add(minArrT);
        frame.add(maxArrT);
        frame.add(minSerT);
        frame.add(maxSert);
        frame.add(queue);
        frame.add(start);

        frame.setVisible(true);
    }
    public void actionPerformed(ActionEvent e){
        Integer NClients = Integer.parseInt(nClients.getText());
        Integer TLim = Integer.parseInt(tLim.getText());
        Integer MinArrT = Integer.parseInt(minArrT.getText());
        Integer MaxArrT = Integer.parseInt(maxArrT.getText());
        Integer MinSerT = Integer.parseInt(minSerT.getText());
        Integer MaxSerT = Integer.parseInt(maxSert.getText());
        Integer Queue = Integer.parseInt(queue.getText());

        list.add(NClients);
        list.add(TLim);
        list.add(MinArrT);
        list.add(MaxArrT);
        list.add(MinSerT);
        list.add(MaxSerT);
        list.add(Queue);
        started = true;
    }


    private void newFrame(){

        frame=new JFrame("Queues");
        frame.setSize(600,400);
        textArea=new JTextArea();
        textArea.setBounds(20,20,540,340);
        frame.add(textArea);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    public List<Integer> getInitValues (){

        while (!started)
        {
            start.setName("Start");
        }
        newFrame();
        return list;
    }
    public void insert(String string){
        textArea.setText(string);
    }
}
