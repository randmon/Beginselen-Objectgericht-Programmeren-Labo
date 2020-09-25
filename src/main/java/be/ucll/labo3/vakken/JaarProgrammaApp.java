package be.ucll.labo3.vakken;

public class JaarProgrammaApp {
    public static void main(String[] args) {
        Vak bop = new Vak("Beginselen van Objectgericht Programmeren", 5);
        bop.berekenWeekUren();

        Vak cs = new Vak("Computersystemen", 6);
        cs.berekenWeekUren();

        Vak algo = new Vak("Algoritmisch denken", 3);
        algo.berekenWeekUren();

        Vak web1 = new Vak("Webontwikkeling 1", 4);
        web1.berekenWeekUren();
    }
}
