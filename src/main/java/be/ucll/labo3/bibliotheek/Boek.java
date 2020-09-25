package be.ucll.labo3.bibliotheek;

public class Boek {
    private String titel, auteur;
    private int aantalPaginas, aantalHoofdstukken;

    public Boek(String titel, String auteur, int aantalPaginas, int aantalHoofdstukken) {
        this.titel = titel;
        this.auteur = auteur;
        this.aantalPaginas = aantalPaginas;
        this.aantalHoofdstukken = aantalHoofdstukken;
    }

    public void aanHetLezen() {
        System.out.println(
                "\nJe bent het boek " + titel + " van auteur " + auteur + " aan het lezen." +
                "\nDit is een boek van " + aantalPaginas + " pagina's dik.");
    }

    public void gemiddeldePaginas() {
        System.out.println(
                "Het gemiddeld aantal pagina's per hoofdstuk zal zijn: " +
                aantalPaginas / aantalHoofdstukken
        );
    }
}
