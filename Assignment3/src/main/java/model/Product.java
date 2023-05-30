package model;

public class Product {
    private int id;
    private String nume;

    private int numar;


    public Product(int id, String nume, int numar) {
        this.id = id;
        this.nume = nume;
        this.numar = numar;
    }
    public Product(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public int getNumar() {
        return numar;
    }

    public void setNumar(int numar) {
        this.numar = numar;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", nume='" + nume + '\'' +
                ", numar=" + numar +
                '}';
    }
}
