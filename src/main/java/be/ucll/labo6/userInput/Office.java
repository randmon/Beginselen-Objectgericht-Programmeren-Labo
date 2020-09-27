package be.ucll.labo6.userInput;

import java.util.ArrayList;
import java.util.List;

public class Office {

    private List<Patient> lijstPatienten = new ArrayList<>();
    private int id;

    public Office(int id) {
        this.id = id;
    }

    public void addPatient(Patient p) {
        if (lijstPatienten.size() == 5) {
            throw new IllegalArgumentException("Je lijst is vol!");
        }
        if (lijstPatienten.contains(p)) {
            throw new IllegalArgumentException(p + " zit al in deze lijst!");
        }
        lijstPatienten.add(p);
    }

    public void printLijst() {
        for (Patient p : lijstPatienten) {
            System.out.println(p);
        }
    }

    public List<Patient> getLijstPatienten() {
        return lijstPatienten;
    }

    public void removePatient(Patient p) {
        lijstPatienten.remove(p);
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Office (ID: " + id + ")";
    }
}
