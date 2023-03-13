public class Monom {
    Integer coef;
    Integer exp;
    public Monom(Integer co,Integer exp){
        coef = co;
        this.exp = exp;
    }

    public Monom suma(Monom m2){
        Monom mono = new Monom(0,0);
        mono.exp = this.exp;
        mono.coef = this.coef + m2.coef;
        return mono;
    }

    public Monom scadere(Monom m2){
        Monom mono = new Monom(0,0);
        mono.exp = this.exp;
        mono.coef = this.coef - m2.coef;
        return mono;
    }

}