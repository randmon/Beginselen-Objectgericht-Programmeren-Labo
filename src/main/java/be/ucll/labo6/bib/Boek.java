package be.ucll.labo6.bib;

public class Boek {

    private String titel, auteur;
    private int aantalPaginas, aantalHoofdstukken;

    public Boek(String titel, String auteur, int aantalPaginas, int aantalHoofdstukken) {
        setTitel(titel);
        setAuteur(auteur);
        setAantalPaginas(aantalPaginas);
        setAantalHoofdstukken(aantalHoofdstukken);
    }

    public void setTitel(String titel) {
        if (titel == null || titel.trim().isEmpty()) {
            this.titel = "Untitled";
        } else {
            this.titel = titel;
        }
    }

    public void setAuteur(String auteur) {
        if (auteur == null || auteur.trim().isEmpty()) {
            this.auteur = "Unknown";
        } else {
            this.auteur = auteur;
        }
    }

    public void setAantalPaginas(int aantalPaginas) {
        if (aantalPaginas<1) {
            throw new IllegalArgumentException("A book needs pages!");
        }
        this.aantalPaginas = aantalPaginas;
    }
    public void setAantalHoofdstukken(int aantalHoofdstukken) {
        if (aantalHoofdstukken<1) {
            throw new IllegalArgumentException("A book needs at least one chapter!");
        }
        this.aantalHoofdstukken = aantalHoofdstukken;
    }

    @Override
    public String toString() {
        return "'" + titel + "'" +
                ", by " + auteur +
                " (" + aantalPaginas + " pages, " +
                aantalHoofdstukken + " chapters)";
    }
}
