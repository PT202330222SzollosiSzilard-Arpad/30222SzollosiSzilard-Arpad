package model;

public class Customer {
    private int id;

    private String nume;

    public String getNume() {
        return nume;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public Customer(int id, String nume) {
        this.id = id;
        this.nume = nume;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", nume='" + nume + '\'' +
                '}';
    }

    public Customer(){}
}
