package be.ucll.labo5;

public class Vak {
    private String naam;
    private int studiePunten;

    public Vak(String naam, int studiePunten) {
        setNaam(naam);
        setStudiePunten(studiePunten);
    }

    public void berekenWeekUren() {
        int totaal = studiePunten * 26 / 16;
        System.out.println(naam + ": " + totaal + " u/week");
    }


    public void setStudiePunten(int studiePunten) {
        if (studiePunten<1 || studiePunten>20) {
            throw new IllegalArgumentException("Studiepunten moet een getal zijn tussen 3 en 20!");
        }
        this.studiePunten = studiePunten;
    }

    public void setNaam(String naam) {
        if (naam == null || naam.trim().isEmpty()) {
            throw new IllegalArgumentException("Naam mag niet leeg zijn!");
        }
        this.naam = naam;
    }
}
