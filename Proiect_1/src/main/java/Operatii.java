import java.util.*;

public class Operatii {

    public String adunare(Polinom p1,Polinom p2) {
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

        Set<Integer> keysop = p2.polinom.keySet();
        for(Integer key : p2.polinom.keySet()){
            rez.polinom.put(key,p2.polinom.get(key));
        }
        String rezultat = new String();

        for(Map.Entry<Integer ,Monom> entry : rez.polinom.entrySet()){
            int exp = entry.getKey();
            Monom monom = entry.getValue();
            if(monom.coef > 0){
                rezultat = rezultat+"+" +monom.coef+"x^"+exp;
            }
            else{
                rezultat = rezultat+monom.coef+"x^"+exp;
            }

        }

        return rezultat;
    }

    public String scadere(Polinom p1,Polinom p2) {
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

        Set<Integer> keysop = p2.polinom.keySet();
        for(Integer key : p2.polinom.keySet()){
            Monom m1 = new Monom(-p2.polinom.get(key).coef,p2.polinom.get(key).exp);
            rez.polinom.put(key,m1);
        }
        String rezultat = new String();

        for(Map.Entry<Integer ,Monom> entry : rez.polinom.entrySet()){
            int exp = entry.getKey();
            Monom monom = entry.getValue();
            if(monom.coef > 0){
                rezultat = rezultat+"+" +monom.coef+"x^"+exp;
            }
            else{
                rezultat = rezultat+monom.coef+"x^"+exp;
            }

        }

        return rezultat;
    }
}
