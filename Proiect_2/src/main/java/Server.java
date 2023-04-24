import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Server implements Runnable{

    private BlockingQueue<Task> tasks;
    private AtomicInteger waitingPeriod;

    private int nr;

    private int maxTasksPerQueue;
    public Server(int x , int z){
        this.nr = x;
        this.maxTasksPerQueue = z;
        this.tasks = new LinkedBlockingQueue<>();
        this.waitingPeriod = new AtomicInteger();
        waitingPeriod.set(0);
    }

    public void run(){
        while (true){
            try{
                Thread.sleep(1000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            if (tasks.size()>0){
                tasks.element().setServiceTime(tasks.element().getServiceTime()-1);

                if (tasks.element().getServiceTime()==0){
                    try{
                        tasks.take();
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void addTask(Task newTask) {
        try {
            tasks.put(newTask);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        waitingPeriod.getAndAdd(newTask.getServiceTime());
    }
    public int getWaitingPeriod() {
        return waitingPeriod.get();
    }

    public int getId(){
        return nr;
    }
    public void setId(int x){
        this.nr = x;
    }


    public int qSize(){
        return tasks.size();
    }
    public Task[] getTasks() {
        BlockingQueue<Task> cpy = new LinkedBlockingQueue<>(tasks);
        return cpy.toArray(new Task[cpy.size()]);
    }



}
