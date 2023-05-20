package model;

public class Orders {

    private int customerId;
    private int orderId;

    private String orderDate;

    private float total;

    public Orders(int x , int y , String z , float a){
        this.customerId = x;
        this.orderId = y;
        this.orderDate = z;
        this.total = a;

    }
    public void setCustomer_id(int x){
        this.customerId = x;
    }

    public void setOrder_id(int x){
        this.orderId = x;
    }

    public void setOrder_date(String x){
        this.orderDate = x;
    }
    public void setTotal(float x){
        this.total = x;
    }

    public int getCustomer_id(){
        return this.customerId;
    }

    public int getOrder_id(){
        return this.orderId;
    }

    public String getOrder_date(){
        return this.orderDate;
    }

    public float getTotal(){
        return this.total;
    }
}
