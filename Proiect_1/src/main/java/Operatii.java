import java.util.HashMap;
import java.util.Objects;
import java.util.Set;

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

        Set<Integer> keysop = p2.polinom.keySet();
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
                Monom m1 = new Monom(-p1.polinom.get(key1).coef,p1.polinom.get(key1).exp);
                rez.polinom.put(key1,m1);

            }
        }

        Set<Integer> keysop = p2.polinom.keySet();
        for(Integer key : p2.polinom.keySet()){
            rez.polinom.put(key,p2.polinom.get(key));
        }
        return rez;
    }
}
