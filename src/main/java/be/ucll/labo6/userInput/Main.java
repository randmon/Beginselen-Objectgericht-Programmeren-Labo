package be.ucll.labo6.userInput;

import java.text.ParseException;
import java.util.Scanner;

public class Main {

    static Database database = new Database();
    static Office currentOffice;
    static Patient currentPatient;
    static Scanner s  = new Scanner(System.in);
    static String input;

    public static void main(String[] args) {

        currentOffice = null;
        input = "";
        System.out.println("\nWelcome!");

        while (!input.equals("stop")) {
            System.out.print("\nGive input (\"help\" for list): ");
            input = s.next();
            handleInput();
        }

        System.out.println("\nGoodbye...");
    }

    //Deal with main branch of commands
    public static void handleInput() {
        switch (input) {
            case "new":
                createNew();
                break;
            case "add":
                if (database.allOffices.size() == 0){
                    System.out.println("There are no offices yet!");
                } else if (database.allPatients.size() == 0) {
                    System.out.println("There are no patients yet!");
                } else {
                    addPatientToOffice();
                }
                break;
            case "alloffices":

                database.seeAllOffices();
                break;
            case "allpatients":
                database.seeAllPatients();
                break;
            case "stop":
                return;
            case "help":
                help();
                break;
            case "hello":
                System.out.println("Hey there!");
                break;
            default:
                System.out.println("I don't understand \"" + input + "\"!");
                break;
        }
    }

    //Choose what to create
    public static void createNew() {
        System.out.print("What do you want to create? (patient/office) : ");
        input = s.next();

        switch (input) {
            case "p":
            case "patient":
                createNewPatient();
                break;
            case "o":
            case "office":
                createNewOffice();
                break;
            case "stop":
                return;
            default:
                dontUnderstand();
                break;
        }
    }

    //Create a new patient and add to database
    public static void createNewPatient() {
        String newInput;

        //Name
        String ln, fn;
        while (true) {
            try {
                System.out.print("\rLast name: ");
                ln = s.next();
                if (ln.equals("stop")) return;
                Patient.checkNaam(ln);

                System.out.print("\rFirst name: ");
                fn = s.next();
                if (fn.equals("stop")) return;
                Patient.checkNaam(fn);

                break;
            } catch (EmptyFieldException efe) {
                System.out.println("Name can't be empty!");
            }
        }

        //Birthdate
        String gd;
        while (true) {
            try {
                System.out.print("\rBirthdate (dd/MM/yyyy): ");
                gd = s.next();
                Patient.checkDate(gd);
                if (gd.equals("stop")) return;

                break;
            } catch (ParseException e) {
                System.out.println("Invalid birthdate format! Try dd/MM/yyyy.");
            } catch (EmptyFieldException efe) {
                System.out.println("Birthdate can't be empty!");
            } catch (IllegalArgumentException iae) {
                System.out.println("Invalid date!");
            }
        }

        //Weight and height
        int kg, l;
        while (true) {
            try {
                System.out.print("\rWeight (kg): ");
                newInput = s.next();
                if (newInput.equals("stop")) return;
                kg = Integer.parseInt(newInput);

                System.out.print("\rHeight (cm): ");
                newInput = s.next();
                if (newInput.equals("stop")) return;
                l = Integer.parseInt(newInput);

                break;
            } catch (Exception e) {
                System.out.println("You can only type numbers!");
            }
        }



        Patient p = new Patient(ln, fn, gd, kg, l, database.nextIDP);
        database.addPatient(p);

        System.out.println(p + " has been added to the database.");

    }

    //Create new office and add it to database
    public static void createNewOffice() {
        Office o = new Office(database.nextIDO);
        database.addOffice(o);
        System.out.println(o + " has been added to the database.");
    }

    //Add a patient to an office
    public static void addPatientToOffice() {
        while (true) {
            while (true) {
                chooseOfficeToAdd();
                if (input.equals("stop")) {
                    input = "";
                    return;
                } else if (!input.equals("1")) { //Office chosen correctly, we can choose patient
                    break;
                }
            }
            while (true) {
                choosePatientToAdd();
                if (input.equals("stop")) {
                    input = "";
                    return;
                } else if (input.equals("1")) { //Need to choose office again
                    break;
                } else if (!input.equals("2")) { //Patient chosen correctly, we can quit
                    return;
                }
            }
        }
    }

    //Choose office to add a person to
    private static void chooseOfficeToAdd() {
        while (true) {
            System.out.print("\rGive office ID (\"list\" to view all) : ");
            input = s.next();

            if (input.equals("stop")){
                return;
            } else if (input.equals("list")) {
                database.seeAllOffices();
            } else {
                try {
                    //If office exists
                    if (Integer.parseInt(input) < database.nextIDO && Integer.parseInt(input)>= 0) {
                        //Set selected office
                        currentOffice = database.allOffices.get(Integer.parseInt(input));
                        System.out.println(currentOffice + " selected. Verifying...");

                        //Check if current office has space available
                        //If office is full
                        if (currentOffice.getLijstPatienten().size() == 5) {
                            System.out.println("This office is already full!");
                            while (true) {
                                System.out.print("\rChoose another office (1) / Remove patient from this office (2): ");
                                input = s.next();
                                if (input.equals("1")) {
                                    break;
                                } else if (input.equals("2")) {
                                    removePatientFromCurrentOffice();
                                    return;
                                } else {
                                    dontUnderstand();
                                }
                            }
                        //If office is not full
                        } else {
                            return;
                        }
                    } else { //If number is invalid or office doesn't exist
                        System.out.println("That office doesn't exist!");
                    }
                } catch (Exception e) {
                    System.out.println("An ID has only numbers!");
                }
            }
        }
    }

    //Choose patient to remove from current office
    private static void removePatientFromCurrentOffice() {

        while(true) {

            System.out.print("\rGive patient ID (\"list\" to view office list) : ");
            input = s.next();

            if (input.equals("stop")){
                return;
            } else if (input.equals("list")) {
                currentOffice.printLijst();
            } else {

                try {
                    //If patient exists
                    if (Integer.parseInt(input) < database.nextIDP && Integer.parseInt(input) >= 0) {
                        //Check if patient is in office
                        if (!currentOffice.getLijstPatienten().contains(currentPatient)) {
                            System.out.println(currentPatient + " is not in this office!");

                        } else {
                            currentOffice.removePatient(currentPatient);
                            System.out.println(currentPatient + " has been removed from " + currentOffice);
                        }
                        break;
                    }
                } catch (Exception e) {
                    System.out.println("An ID has only numbers!");
                }
            }
        }
    }

    //Choose patient to add to current office
    private static void choosePatientToAdd() {
        while (true) {
            System.out.print("\rGive patient ID (\"list\" to view all) : ");
            input = s.next();

            if (input.equals("stop")){
                return;
            } else if (input.equals("list")) {
                database.seeAllPatients();
            } else {
                try {
                    //If patient exists
                    if (Integer.parseInt(input) < database.nextIDP && Integer.parseInt(input)>= 0) {

                        currentPatient = database.allPatients.get(Integer.parseInt(input));

                        //Check if current office already has this patient
                        //If patient is already in office
                        if (currentOffice.getLijstPatienten().contains(currentPatient)) {
                            System.out.println("This patient is already in this office!");

                            while (true) {
                                System.out.print("\rChoose another office (1) / Choose another patient (2): ");
                                input = s.next();
                                if (input.equals("1")) {
                                    return;
                                } else if (input.equals("2")) {
                                    return;
                                } else {
                                    dontUnderstand();
                                }
                            }
                        //If office does not have this patient
                        } else {
                            currentOffice.addPatient(currentPatient);
                            System.out.println(currentPatient + " added to " + currentOffice);
                            input = "";
                            return;
                        }
                    } else { //If number is invalid or patient doesn't exist
                        System.out.println("That patient doesn't exist!");
                    }
                } catch (Exception e) {
                    System.out.println("An ID has only numbers!");
                }
            }
        }
    }

    //Print invalid command message
    private static void dontUnderstand() {
        System.out.println("I don't understand \"" + input + "\"!");
    }

    //List all available commands
    public static void help() {
        System.out.println("\nCommands available:");
        System.out.println(" - new:             create new patient or office");
        System.out.println(" - add:             add a patient to an office");
        System.out.println(" - alloffices:      view all offices in database");
        System.out.println(" - allpatients:     view all patients in database");
        System.out.println(" - stop:            exit");

    }
}
