public class MainClass {
    public static void main(String[] Args){
        Monom mon1=new Monom(2);
        Monom mon2=new Monom (3);
        Polinom pol = new Polinom(0,mon1);
        pol.add(1,mon2);
        Polinom pol2 = new Polinom(0,mon1);
        pol2.add(1,mon1);
    }
}
