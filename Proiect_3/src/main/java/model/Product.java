package model;

public class Product {


    private int productId;
    private String productName;

    private float productPrice;


    public Product(int x , String n, float pr){
        this.productPrice = pr;
        this.productName = n;
        this.productId = x;
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
