package be.ucll.labo3.vakken;

public class Vak {
    private String naam;
    private int studiePunten;

    public Vak(String naam, int studiePunten) {
        this.naam = naam;
        this.studiePunten = studiePunten;
    }

    public void berekenWeekUren() {
        int totaal = studiePunten * 26 / 16;
        System.out.println(naam + ": " + totaal + " u/week");
    }
}
