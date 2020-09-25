package be.ucll.labo4;

public class Main {
    public static void main(String[] args) {
        Teller t = new Teller();

        t.add();
        t.add();

        t.zetUit();

        System.out.println(t.getWaarde());

        t.add();
        System.out.println(t.getWaarde());

        t.reset();
        System.out.println(t.getWaarde());

        t.zetAan();
        t.reset();
        System.out.println(t.getWaarde());
    }
}
