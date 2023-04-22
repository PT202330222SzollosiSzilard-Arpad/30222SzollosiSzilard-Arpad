import java.util.List;

public class ConcreteStrategyTime implements Strategy{
    @Override
    public void addTask(List<Server> servers, Task t) {
        Server shortestWait = servers.get(0);
        for (Server s : servers) {
            if (s.getWaitingPeriod() < shortestWait.getWaitingPeriod()) {
                shortestWait = s;
            }
        }
        shortestWait.addTask(t);
    }
}
