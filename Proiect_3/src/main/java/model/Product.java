package model;

public class Product {


    private int productId;
    private String productName;

    private float productPrice;


    public Product(){

    }

    public float getProductPrice() {
        return productPrice;
    }

    public String getProductName(){
        return  productName;
    }

    public int getProductId(){
        return productId;
    }

    public void setProductId(int x){
        this.productId = x;
    }

    public void setProductName(String x){
        this.productName = x;
    }

    public void setProductPrice(float x){
        this.productPrice = x;
    }



}
