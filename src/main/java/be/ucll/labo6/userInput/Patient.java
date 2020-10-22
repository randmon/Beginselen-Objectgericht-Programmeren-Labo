package be.ucll.labo6.userInput;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Patient {
    private String naam, voornaam;
    private LocalDate geboortedatum;
    private final DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private int gewicht;
    private int bmi;
    private final int id;
    private double lengte;

    /** Constructor
        String naam, voornaam - name of patient
        String geboortedatumddMMYY - dat of birth, "ddMMYY" is the date format
        int gewichtKG - patient's weight in kilograms
        double lengteCM - patient's height in centimeters
     */
    public Patient(Scanner scanner, int id) {
        setNaam(scanner);
        setVoornaam(scanner);
        setGeboortedatum(scanner);
        setGewicht(scanner);
        setLengte(scanner);
        calculateBMI();
        this.id = id;
    }

    private void setNaam(Scanner scanner) {
        String fn;
        while (true) {
            try {
                System.out.print("\rFirst name: ");
                fn = scanner.next();
                checkNotEmpty(fn);
                this.naam = fn;
                break;
            } catch (EmptyFieldException e) {
                System.out.println("Name can't be empty!");
            }
        }
    }

    private void setVoornaam(Scanner scanner) {
        String ln;
        while (true) {
            try {
                System.out.print("\rLast name: ");
                ln = scanner.next();
                checkNotEmpty(ln);
                this.voornaam = ln;
                break;
            } catch (EmptyFieldException e) {
                System.out.println("Name can't be empty!");
            }
        }
    }

    //GEBOORTEDATUM
    /* setGeboortedatum - sets birthdate*/
    private void setGeboortedatum(Scanner scanner){
        LocalDate gdDate;
        String gdString;
        while (true) {
            try {
                System.out.print("\rBirthdate (dd-MM-YYYY): ");
                gdString = scanner.next();
                checkNotEmpty(gdString);
                gdDate = checkFormat(gdString);
                checkNotFuture(gdDate);
                geboortedatum = gdDate;
                break;
            } catch (EmptyFieldException e) {
                System.out.println("Date can't be empty!");
            } catch (DateTimeParseException e) {
                System.out.println("Invalid birthdate format!");
            } catch (FutureDateException e) {
                System.out.println("Date can't be in the future!");
            }
        }
    }

    //GEWICHT, LENGTE EN BMI
    public void setGewicht(Scanner scanner) {
        int g;
        while (true) {
            try {
                System.out.print("\rWeight (kg): ");
                g = Integer.parseInt(scanner.next());
                checkNotNegative(g);
                this.gewicht = g;
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Weight has to be a positive number!");
            }
        }
    }

    public void setLengte(Scanner scanner) {
        double l;
        while (true) {
            try {
                System.out.print("\rHeight (cm): ");
                l = Integer.parseInt(scanner.next());
                checkNotNegative((int) l);
                this.lengte = l/100;
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Height has to be a positive number!");
            }
        }
    }

    public void calculateBMI() {
        bmi = (int) Math.round(gewicht / (lengte * lengte));
    }

    //GETTERS
    public String getNaam() {
        return naam;
    }
    public String getVoornaam() {
        return voornaam;
    }
    public String getGeboortedatumString() {
        return geboortedatum.format(dtFormatter);
    }
    public int getGewicht() {
        return gewicht;
    }
    public double getLengte() {
        return lengte;
    }
    public int getId() {
        return id;
    }
    public int getBmi() {
        return bmi;
    }
    @Override
    public String toString() {
        return naam + " " + voornaam + " (ID: " + id + ")";
    }

    //VALIDATION METHODS
    private void checkNotEmpty(String input) throws EmptyFieldException {
        if (input == null || input.trim().isEmpty()) {
            throw new EmptyFieldException();
        }
    }

    private void checkNotNegative(int input) throws IllegalArgumentException {
        if (input < 0) {
            throw new IllegalArgumentException();
        }
    }

    //Attempts to parse the String into a LocalDate according to our format
    private LocalDate checkFormat(String bdString) throws DateTimeParseException{
        return LocalDate.parse(bdString, dtFormatter);
    }

    //Throws IllegalArgumentException if date is in the future
    private void checkNotFuture(LocalDate temp) throws FutureDateException {
        if (LocalDate.now().compareTo(temp) < 0) {
            throw new FutureDateException();
        }
    }
}
