public class Task {
    private int arrivalTime;
    private int serviceTime;

    public Task(int arrT, int srvT){
        this.arrivalTime = arrT;
        this.serviceTime = srvT;
    }


    public int getServiceTime() {
        return serviceTime;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }
}
