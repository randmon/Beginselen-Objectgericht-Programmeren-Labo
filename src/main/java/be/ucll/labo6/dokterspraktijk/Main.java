package be.ucll.labo6.dokterspraktijk;

public class Main {

    public static void main(String[] args) {
        Praktijk praktijk1 = new Praktijk();

        Patient bernie = new Patient("Johnson", "Bernie", "101093", 100, 195);
        Patient sarah = new Patient("Montgomery", "Sarah", "130120", 8, 60);
        Patient linda = new Patient("Johnson", "Linda", "111193", 53, 167);
        Patient linda2 = new Patient("Johnson", "Linda", "170871", 76, 160);
        Patient cristina = new Patient("Marques", "Cristina", "100998", 60, 165);
        Patient kris = new Patient("Keersmaekers", "Kris", "080498", 65, 170);

        praktijk1.addPatient(bernie);
        //praktijk1.addPatient(bernie);
        praktijk1.addPatient(sarah);
        praktijk1.addPatient(linda);
        praktijk1.addPatient(linda2);
        praktijk1.addPatient(cristina);
        //praktijk1.addPatient(kris);

        praktijk1.printLijst();
    }
}
