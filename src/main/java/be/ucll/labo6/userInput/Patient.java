package be.ucll.labo6.userInput;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Patient {
    private String naam, voornaam;
    private Date geboortedatum;
    private int gewicht, bmi, id;
    private double lengte;

    /** Constructor
        String naam, voornaam - name of patient
        String geboortedatumddMMYY - dat of birth, "ddMMYY" is the date format
        int gewichtKG - patient's weight in kilograms
        double lengteCM - patient's height in centimeters
     */
    public Patient(String naam, String voornaam, String geboortedatumddMMYY, int gewichtKG, double lengteCM, int id) {
        setNaam(naam);
        setVoornaam(voornaam);
        setGeboortedatum(geboortedatumddMMYY);
        setGewicht(gewichtKG);
        setLengte(lengteCM);

        this.id = id;

        calculateBMI();
    }

    /* setNaam - sets naam, can't be empty*/
    public void setNaam(String naam) {
        this.naam = naam;
    }

    /* setNaam - sets name, can't be empty*/
    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }

    public static void checkNaam(String naam) throws EmptyFieldException{
        if (naam == null || naam.trim().isEmpty()) {
            throw new EmptyFieldException();
        }
    }

    /* setGeboortedatum - sets birthdate*/
    public void setGeboortedatum(String geboortedatumString) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            geboortedatum = dateFormat.parse(geboortedatumString);

        } catch (ParseException e) {
            System.out.println("How did we get here?");
        }
    }

    public static void checkDate(String geboortedatumString) throws ParseException, EmptyFieldException, IllegalArgumentException{
        if (geboortedatumString == null || geboortedatumString.trim().isEmpty()) {
            throw new EmptyFieldException();
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date now = new Date();
        dateFormat.format(now);
        Date dateTry = dateFormat.parse(geboortedatumString);

        if (now.compareTo(dateTry) < 0) {
            throw new IllegalArgumentException();
        }
    }



    /* setGewicht - sets weight, must be greater than 0*/
    public void setGewicht(int gewicht) {
        if (gewicht < 0) { throw new IllegalArgumentException("Gewicht moet positief zijn!");}
        this.gewicht = gewicht;
    }

    /* setLengte - must be greater than 0*/
    public void setLengte(double lengte) {
        if (lengte < 0) { throw new IllegalArgumentException("Lengte moet positief zijn!");}
        this.lengte = lengte/100;
    }

    @Override
    public String toString() {
        return voornaam + " " + naam + " (ID: " + id + ")";
    }

    //Calculates bmi and rounds it to a whole number
    //This always gives a valid value because we verify weight and height before
    public void calculateBMI() {
        bmi = (int) Math.round(gewicht / (lengte * lengte));
    }

    public int getId() {
        return id;
    }

}
