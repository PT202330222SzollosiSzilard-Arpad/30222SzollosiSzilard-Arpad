import java.util.ArrayList;
import java.util.List;

public class Scheduler {
    private List<Server> servers;
    private int maxNoServers;
    private int maxTasksPerServer;
    private Strategy strategy;

    public Scheduler(int maxNoServers, int maxTasksPerServer) {
        servers = new ArrayList<>();
        this.maxNoServers = maxNoServers;
        this.maxTasksPerServer = maxTasksPerServer;
        for (int i = 0; i < maxNoServers; i++) {
            servers.add(new Server());
            Thread t = new Thread(servers.get(i));
            t.start();
        }
    }

    public void changeStrategy(SelectionPolicy policy){
        //apply strat pattern to inst the strat
        //strat corresponding to policy

        if (policy == SelectionPolicy.SHORTEST_QUEUE) {
            strategy = new ConcreteStrategyQueue();
        }
        if (policy == SelectionPolicy.SHORTEST_TIME) {
            strategy = new ConcreteStrategyTime();
        }
    }

    public void dispatchTask(Task t){
        strategy.addTask(servers, t);
    }

    public List<Server> getServers(){
        return servers;
    }

}
