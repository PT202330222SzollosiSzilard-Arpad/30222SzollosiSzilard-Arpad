import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class SimulationManager implements Runnable {
    public int timeLimit = 30;
    public int minProcessingTime;
    public int maxProcessingTime = 5;
    public int min = 2;
    public int numberOfServers = 4;
    public int numberOfClients = 1;
    public int maxArrT = timeLimit-10;

    public SelectionPolicy selectionPolicy = SelectionPolicy.SHORTEST_TIME;

    private Scheduler scheduler = new Scheduler(numberOfServers, numberOfClients, selectionPolicy);
    private SimulationFrame frame ;
    private List<Task> generatedTasks;

    public SimulationManager() {
        this.frame=new SimulationFrame();
        getFields(frame.getInitValues());
        this.scheduler = new Scheduler(numberOfServers,3,selectionPolicy);
        generatedTasks= Collections.synchronizedList(new ArrayList<>());
        generateNRandomTasks();
    }

    private void generateNRandomTasks() {


        generatedTasks = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < numberOfClients; i++) {
            int arrivalTime = random.nextInt(maxArrT);

                int serviceTime = random.nextInt(maxProcessingTime );
                if(serviceTime < minProcessingTime)
                    serviceTime = minProcessingTime;
                generatedTasks.add(new Task(arrivalTime, serviceTime));

        }
        generatedTasks.sort((Comparator.comparing(Task::getArrivalTime)));
        for (int i=0;i<numberOfClients;i++)
            generatedTasks.get(i).setId(i);
    }

    private String printer(){
        String stack="Waiting: ";
        for (Task i:generatedTasks)
        {
            stack=stack+ "("+i.getId()+","+i.getArrivalTime()+","+i.getServiceTime()+");";
        }
        stack=stack+"\n";
        for (Server i:scheduler.getServers()){
            stack=stack+"Queue "+(i.getId()+1)+": ";
            if (i.qSize()==0)
                stack=stack+"Ready";
            else {
                Task[] tasks=i.getTasks();
                for(int j=0;j<i.qSize();j++)
                    stack=stack+"("+tasks[j].getId()+","+tasks[j].getArrivalTime()+","+tasks[j].getServiceTime()+");";

            }
            stack=stack+"\n";
        }
        return stack;
    }

    public void run() {
        Integer currentTime=0;
        String display;
        String fin=" ";
        while (currentTime<timeLimit)
        {
            int i=0;
            while(i==0)
            {
                if (generatedTasks.size()!=0){
                    Task j=generatedTasks.get(0);
                    if (j.getArrivalTime() == currentTime)
                    {
                        scheduler.dispatchTask(j);
                        generatedTasks.remove(j);
                    }
                    else if (j.getArrivalTime()>currentTime)
                    {
                        i=-1;
                    }
                }

                if (generatedTasks.size()==0)
                    i=-1;
            }
            display="Time "+ currentTime +"\n"+printer();
            fin=fin+display;
            System.out.println(display);
            frame.insert(display);
            currentTime++;
            try{
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try (FileWriter fileWriter = new FileWriter("log.txt"))
        {
            fileWriter.write(fin);
        }
        catch (IOException ioException)
        {
            ioException.printStackTrace();
        }

    }


    public void getFields(List<Integer> list){
        this.numberOfClients=list.get(0);
        this.timeLimit=list.get(1);
        this.min=list.get(2);
        this.maxArrT=list.get(3);
        this.minProcessingTime=list.get(4);
        this.maxProcessingTime=list.get(5);
        this.numberOfServers=list.get(6);
    }

    public static void main(String[] args) {
        SimulationManager gen = new SimulationManager();
        gen.generateNRandomTasks();
        Thread t = new Thread(gen);
        t.start();

    }
}
