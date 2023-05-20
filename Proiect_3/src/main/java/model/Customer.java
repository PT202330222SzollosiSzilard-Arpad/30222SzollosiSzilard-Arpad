package model;

public class Customer {
    private int customerId;

    private String customerName;
    public Customer(int x , String s){
        this.customerId = x;
        this.customerName = s;
    }
    public void setCustomerId(int x){
        this.customerId = x;
    }

     public int getCustomerId(){
        return this.customerId;
    }

    public String getName(){
        return this.customerName;
    }

    public void setName(String name){
        this.customerName = name;
    }

}
