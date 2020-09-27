package be.ucll.labo5;

public class JaarProgrammaApp {
    public static void main(String[] args) {
        try{

        Vak bop = new Vak("Beginselen van Objectgericht Programmeren", 5);
        bop.berekenWeekUren();

    } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
