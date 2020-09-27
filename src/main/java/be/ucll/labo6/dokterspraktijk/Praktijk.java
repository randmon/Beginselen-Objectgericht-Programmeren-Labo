package be.ucll.labo6.dokterspraktijk;

import java.util.ArrayList;
import java.util.List;

public class Praktijk {

    private List<Patient> lijstPatienten = new ArrayList<>();

    public Praktijk() {
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

    public void removePatient(Patient p) {
        if (lijstPatienten.contains(p)) {
            lijstPatienten.remove(p);
        } else {
            throw new IllegalArgumentException(p + " zit niet in deze lijst!");
        }
    }
}
