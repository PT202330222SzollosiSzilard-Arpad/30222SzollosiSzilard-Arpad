import java.util.*;

public class Operatii {

    public Polinom adunare(Polinom p1,Polinom p2) {
        Polinom rez = new Polinom();

        for(Integer key1 : p1.polinom.keySet()){
            int x = 0;
            for(Integer key2 : p2.polinom.keySet()){
                if(Objects.equals(key1, key2)) {
                    rez.polinom.put(key1, p1.polinom.get(key1).suma(p2.polinom.get(key2)));
                    p2.polinom.remove(key2);
                    x = 1;
                    break;
                }
                }
            if(x == 0){
                    rez.polinom.put(key1,p1.polinom.get(key1));

            }
        }


        for(Integer key : p2.polinom.keySet()){
            rez.polinom.put(key,p2.polinom.get(key));
        }

        return rez;
    }

    public Polinom scadere(Polinom p1,Polinom p2) {
        Polinom rez = new Polinom();

        for(Integer key1 : p1.polinom.keySet()){
            int x = 0;
            for(Integer key2 : p2.polinom.keySet()){
                if(Objects.equals(key1, key2)) {
                    rez.polinom.put(key1, p1.polinom.get(key1).scadere(p2.polinom.get(key2)));
                    p2.polinom.remove(key2);
                    x = 1;
                    break;
                }
            }
            if(x == 0){
                Monom m1 = new Monom(p1.polinom.get(key1).coef,p1.polinom.get(key1).exp);
                rez.polinom.put(key1,m1);

            }
        }


        for(Integer key : p2.polinom.keySet()){
            Monom m1 = new Monom(-p2.polinom.get(key).coef,p2.polinom.get(key).exp);
            rez.polinom.put(key,m1);
        }

        return rez;
    }
    public Polinom mul(Polinom p1,Polinom p2) {

        Polinom rezultat = new Polinom();
        for(Integer key1 : p1.polinom.keySet()){
            Polinom rez = new Polinom();
            for(Integer key2 : p2.polinom.keySet()){

                    rez.polinom.put(key1+key2, p1.polinom.get(key1).mul(p2.polinom.get(key2)));

            }
            rezultat=adunare(rezultat,rez);
        }

        return rezultat;
    }

    public Polinom deriv(Polinom p1) {

        Polinom rezultat = new Polinom();
        for(Integer key1 : p1.polinom.keySet()){
            rezultat.polinom.put(key1-1, p1.polinom.get(key1).deriv());

        }
        return rezultat;
    }

    public Polinom integrare(Polinom p1){
        Polinom rezultat = new Polinom();
        for(Integer key1 : p1.polinom.keySet()){
            rezultat.polinom.put(key1+1, p1.polinom.get(key1).integrare());
        }

        return rezultat;
    }
}
