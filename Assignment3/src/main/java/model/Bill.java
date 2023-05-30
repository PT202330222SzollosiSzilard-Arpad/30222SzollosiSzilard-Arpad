package model;

public record Bill(int id , int idProduct,int idCustomer, int amount) {
    @Override
    public String toString(){
        StringBuilder s = new StringBuilder();
        s.append("id :").append(id).append("\n");
        s.append("Product id : ").append(idProduct).append("\n");
        s.append("Customer id : ").append(idCustomer).append("\n");
        s.append("Amount : ").append(amount).append("\n");

        return s.toString();
    }
}
