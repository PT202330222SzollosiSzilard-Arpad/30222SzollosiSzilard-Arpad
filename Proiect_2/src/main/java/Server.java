import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Server implements Runnable{

    private BlockingQueue<Task> tasks;
    private AtomicInteger waitingPeriod;

    public Server(){
    }

    public void run(){
        while(true){

        }
    }

    public void addTask(Task newTask) {
        try {
            tasks.put(newTask);
            waitingPeriod.getAndAdd(newTask.getServiceTime());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public int getWaitingPeriod() {
        return waitingPeriod.get();
    }

    public List<Task> getTasks() {
        List<Task> taskList = new ArrayList<>();
        for (Task task : tasks) {
            taskList.add(task);
        }
        return taskList;
    }



}
