import java.util.ArrayList;
import java.util.List;

public class SimulationManager implements Runnable{
    public int timeLimit = 100;
    public int maxProcessingTime = 10;
    public int min = 2;
    public int numberOfServers = 3;

    public int numberOfClients = 100;
    public SelectionPolicy selectionPolicy = SelectionPolicy.SHORTEST_TIME;

    private Scheduler scheduler;
    private SimulationFrame frame;
    private List<Task> generatedTasks;

    public SimulationManager(){

    }

    private void generateNRandomTasks() {
        generatedTasks = new ArrayList<>();
        for (int i = 0; i < numberOfClients; i++) {
            int arrivalTime = (int) (Math.random() * timeLimit);
            int serviceTime = (int) (Math.random() * maxProcessingTime) + 1;
            generatedTasks.add(new Task(arrivalTime, serviceTime));
        }
    }


    public void run() {
        int currentTime = 0;
        while (currentTime < timeLimit) {
            for (Task t : generatedTasks) {
                if (t.getArrivalTime() == currentTime) {
                    scheduler.dispatchTask(t);
                }
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            currentTime++;
        }
    }

    public static void main (String[] args){

        SimulationManager gen = new SimulationManager();
        gen.generateNRandomTasks();
        Thread t = new Thread(gen);
        t.start();
    }

}
