package be.ucll.labo6.userInput;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Office {

    private List<Patient> lijstPatienten = new ArrayList<>();
    private final int id;

    public Office(int id) {
        this.id = id;
    }

    public void addPatient(Patient p) {
        if (!isFull() && !lijstPatienten.contains(p)){
            lijstPatienten.add(p);
            System.out.println("    "+p + " added to " + this);
        }
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

    public boolean isFull(){
        return lijstPatienten.size() == 5;
    }

    @Override
    public String toString() {
        return "Office (ID: " + id + ")";
    }

    //INPUT
    public static void createNewOffice(Scanner scanner, int nextIDO) {
    }
}
