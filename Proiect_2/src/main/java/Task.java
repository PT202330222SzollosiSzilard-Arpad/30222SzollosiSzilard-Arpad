public class Task {
    private int arrivalTime;
    private int serviceTime;
    int id;

    public Task(int arrT, int srvT){
        this.arrivalTime = arrT;
        this.serviceTime = srvT;
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getId(){
        return this.id;
    }

    public int getServiceTime() {
        return serviceTime;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setServiceTime(int x){
        this.serviceTime=x;
    }
}
