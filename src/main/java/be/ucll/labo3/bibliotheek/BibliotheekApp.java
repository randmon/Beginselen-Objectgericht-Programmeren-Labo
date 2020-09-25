package be.ucll.labo3.bibliotheek;

public class BibliotheekApp {

    public static void main(String[] args) {

        Boek boekHTMLCSS = new Boek("HTML&CSS", "Duckett", 999, 10);
        boekHTMLCSS.aanHetLezen();
        boekHTMLCSS.gemiddeldePaginas();

        Boek boekJavaScript = new Boek("JavaScript&jQuery", "Duckett", 1400, 12);
        boekJavaScript.aanHetLezen();
        boekJavaScript.gemiddeldePaginas();

    }
}
