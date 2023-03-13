import java.util.HashMap;
import java.util.*;

public class Polinom {
    HashMap <Integer, Monom> polinom = new HashMap<Integer,Monom>();
    Polinom(){};
    Polinom(Integer x , Monom z){
        this.polinom.put(x,z);
    }

    public void add(int i, Monom mon2) {
        this.polinom.put(i,mon2);
    }



}
