package model;

public class Orders {
    private int idproduct;
    private int idcustomer;

    private int id;
    int numar;


    public Orders(int id,int x , int y , int z){
        this.id = id;
        this.idproduct = x;
        this.idcustomer = y;
        this.numar = z;

    }
    public Orders(){}

    public int getIdproduct() {
        return idproduct;
    }

    public void setIdproduct(int idproduct) {
        this.idproduct = idproduct;
    }

    public int getIdcustomer() {
        return idcustomer;
    }

    public void setIdcustomer(int idcustomer) {
        this.idcustomer = idcustomer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumar() {
        return numar;
    }

    public void setNumar(int numar) {
        this.numar = numar;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "idproduct=" + idproduct +
                ", idcustomer=" + idcustomer +
                ", id=" + id +
                ", numar=" + numar +
                '}';
    }
}
