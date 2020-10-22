package be.ucll.labo6.userInput;

import java.util.ArrayList;
import java.util.List;

public class Database {
    public List<Patient> allPatients = new ArrayList<>();
    public List<Office> allOffices = new ArrayList<>();
    private int nextIDP, nextIDO; //IDP = id person, IDO = id office

    public Database() {
        nextIDP = 0;
        nextIDO = 0;
    }

    //CONSULT LISTS
    public void seeAllPatients() {
        if (allPatients.size() == 0){
            System.out.println("There are no patients yet!");
        } else {
            System.out.println("All patients in database:");
            for (Patient p : allPatients) {
                System.out.println("   - " + p);
            }
        }
    }
    public void seeAllOffices() {
        if (allOffices.size() == 0){
            System.out.println("There are no offices yet!");
        } else {
            System.out.println("All offices in database:");
            for (Office o : allOffices) {
                System.out.println("   - " + o);
            }
        }
    }

    //ADD TO DATABASE
    public void addPatient(Patient p) {
        if (allPatients.contains(p)) {
            System.out.println("    That patient already exists in the database");
        } else {
            allPatients.add(p);
            System.out.println("    "+p + " added to the database");
            nextIDP++;
        }
    }
    public void addOffice(Office o) {
        if (allOffices.contains(o)) {
            System.out.println("    That office already exists in the database");
        } else {
            allOffices.add(o);
            System.out.println("    "+o + " added to the database");
            nextIDO++;
        }
    }

    public int getNextIDP() {
        return nextIDP;
    }
    public int getNextIDO() {
        return nextIDO;
    }
}