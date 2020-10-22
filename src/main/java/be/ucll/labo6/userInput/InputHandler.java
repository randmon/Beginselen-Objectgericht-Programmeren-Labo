package be.ucll.labo6.userInput;

import java.util.Scanner;

public class InputHandler {
    static Database database = new Database();
    static Office currentOffice;
    static Patient currentPatient;
    static Scanner scanner = new Scanner(System.in);
    private String input;

    public InputHandler() {
        input = "";
    }

    public void handleInput() {
        while (!input.equals("stop")) {
            System.out.print("\nGive input (\"help\" for list): ");
            input = scanner.next();
            handleMainInput(input);
        }
    }

    private void handleMainInput(String input) {
        switch (input) {
            case "new":
                createNew();
                break;

            case "add":
                if (database.allOffices.size() == 0){
                    System.out.println("    There are no offices yet!");
                } else if (database.allPatients.size() == 0) {
                    System.out.println("    There are no patients yet!");
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
                System.out.println("    Available commands: new | add | alloffices | allpatients | stop");
                break;
            case "hello":
                System.out.println("    Hey there!");
                break;
            default:
                dontUnderstand();
                break;
        }
    }

    //Choose what to create
    private void createNew() {
        while (true) {
            System.out.print("What do you want to create? (patient/office) : ");
            input = scanner.next();

            switch (input) {
                case "p":
                case "patient":
                    Patient p = new Patient(scanner, database.getNextIDP());
                    database.addPatient(p);
                    return;
                case "o":
                case "office":
                    Office o = new Office(database.getNextIDO());
                    database.addOffice(o);
                    return;
                case "stop":
                    return;
                default:
                    dontUnderstand();
                    break;
            }
        }
    }

    //Add patient to office
    private void addPatientToOffice() {
        while (true) {
            while (true) {
                chooseOfficeToAdd();
                if (input.equals("stop")) {
                    input = "";
                    return;
                } else if (!input.equals("1")) { //Office chosen correctly, we can choose patient
                    break;
                }
                //If input is 1 this repeats to choose another office
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
    private void chooseOfficeToAdd() {
        while (true) {
            System.out.print("\rGive office ID (\"list\" to view all) : ");
            input = scanner.next();

            switch (input) {
                case "stop":
                    return;
                case "list":
                    database.seeAllOffices();
                    break;
                case "help":
                    System.out.println("    Available commands: (office ID) | list | stop");
                    break;
                default:
                    try {
                        //If office exists
                        if (Integer.parseInt(input) < database.getNextIDO() && Integer.parseInt(input)>= 0) {
                            //Set selected office
                            currentOffice = database.allOffices.get(Integer.parseInt(input));
                            System.out.println("    "+currentOffice + " selected. Verifying...");
                            input = "";
                            //Check if current office has space available
                            //If office is full
                            if (currentOffice.isFull()) {
                                System.out.println("    This office is already full!");
                                while (true) {
                                    System.out.print("\rChoose another office (1) / Remove a patient from this office (2): ");
                                    input = scanner.next();

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
                            System.out.println("    That office doesn't exist!");
                        }
                    } catch (Exception e) {
                        System.out.println("    An ID only has numbers!");
                    }
                    break;
            }
        }
    }

    private void choosePatientToAdd() {
        while (true) {
            System.out.print("\rGive patient ID (\"list\" to view all) : ");
            input = scanner.next();
            switch (input) {
                case "stop":
                    return;
                case "list":
                    database.seeAllPatients();
                    break;
                case "help":
                    System.out.println("    Available commands: (patient ID) | list | stop");
                    break;
                default:
                    try {
                        //If patient exists
                        if (Integer.parseInt(input) < database.getNextIDP() && Integer.parseInt(input)>= 0) {
                            //Set selected patient
                            currentPatient = database.allPatients.get(Integer.parseInt(input));

                            //Check if current office already has this patient
                            //If patient is already in office
                            if (currentOffice.getLijstPatienten().contains(currentPatient)) {
                                System.out.println("    This patient is already in this office!");

                                while (true) {
                                    System.out.print("\rChoose another office (1) / Choose another patient (2): ");
                                    input = scanner.next();
                                    if (input.equals("1") || input.equals("2") || input.equals("stop")) {
                                        return;
                                    } else {
                                        dontUnderstand();
                                    }
                                }
                            //If office does not have this patient
                            } else {
                                currentOffice.addPatient(currentPatient);
                                input = "";
                                return;
                            }
                        } else { //If number is invalid or patient doesn't exist
                            System.out.println("    That patient doesn't exist!");
                        }
                    } catch (Exception e) {
                        System.out.println("    An ID has only numbers!");
                    }
                    break;
            }
        }
    }

    private void removePatientFromCurrentOffice() {
        while(true) {
            System.out.print("\rGive patient ID to be removed (\"list\" to view all) : ");
            input = scanner.next();

            if (input.equals("list")) {
                currentOffice.printLijst();
            } else {
                try {
                    //If patient exists
                    if (Integer.parseInt(input) < database.getNextIDP() && Integer.parseInt(input) >= 0) {
                        //Check if patient is in office
                        if (!currentOffice.getLijstPatienten().contains(currentPatient)) {
                            System.out.println("    "+currentPatient + " is not in this office!");

                        } else {
                            currentOffice.removePatient(currentPatient);
                            System.out.println("    "+currentPatient + " has been removed from " + currentOffice);
                        }
                        break;
                    }
                } catch (Exception e) {
                    System.out.println("    An ID has only numbers!");
                }
            }
        }
    }


    private void dontUnderstand() {
        System.out.println("I don't understand \"" + input + "\"!");
    }


}
