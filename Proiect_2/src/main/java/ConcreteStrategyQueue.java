import java.util.List;

public class ConcreteStrategyQueue implements Strategy{
    public ConcreteStrategyQueue(){

    }

    @Override
    public void addTask(List<Server> servers, Task t) {
        Server shortestQueue = servers.get(0);
        for (Server s : servers) {
            if (s.getTasks().size() < shortestQueue.getTasks().size()) {
                shortestQueue = s;
            }
        }
        shortestQueue.addTask(t);
    }
}
