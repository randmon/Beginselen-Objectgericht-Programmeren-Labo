package be.ucll.labo6.dokterspraktijk;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Patient {
    private String naam, voornaam;
    private Date geboortedatum;
    private int gewicht, bmi;
    private double lengte;

    /** Constructor
        String naam, voornaam - name of patient
        String geboortedatumddMMYY - dat of birth, "ddMMYY" is the date format
        int gewichtKG - patient's weight in kilograms
        double lengteCM - patient's height in centimeters
     */
    public Patient(String naam, String voornaam, String geboortedatumddMMYY, int gewichtKG, double lengteCM) {
        setNaam(naam);
        setVoornaam(voornaam);
        setGeboortedatum(geboortedatumddMMYY);
        setGewicht(gewichtKG);
        setLengte(lengteCM);

        calculateBMI();
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

    /* setNaam - sets naam, can't be empty*/
    public void setNaam(String naam) {
        if (naam == null || naam.trim().isEmpty()) {
            throw new IllegalArgumentException("Naam mag niet leeg zijn!");
        }
        this.naam = naam;
    }
    /* setNaam - sets name, can't be empty*/
    public void setVoornaam(String voornaam) {
        if (voornaam == null || voornaam.trim().isEmpty()) {
            throw new IllegalArgumentException("Voornaam mag niet leeg zijn!");
        }
        this.voornaam = voornaam;
    }

    /* setGeboortedatum - sets birthdate, can't be empty, can't be greater than current date, throws exception on invalid format*/
    public void setGeboortedatum(String geboortedatumString) {
        if (geboortedatumString == null || geboortedatumString.trim().isEmpty()) {
            throw new IllegalArgumentException("Geboortedatum mag niet leeg zijn!");
        }
        try {
            Date now = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyy");

            dateFormat.format(now);
            geboortedatum = dateFormat.parse(geboortedatumString);

            if (now.compareTo(geboortedatum) < 0) {
                throw new IllegalArgumentException("Dude, are you from the future!?");
            }

        } catch (ParseException e) {
            System.out.println("Invalid birth date format! Try ddMMYY.");
        }
    }

    @Override
    public String toString() {
        return voornaam + " " + naam + " (BMI: " + bmi + ")";
    }

    //Calculates bmi and rounds it to a whole number
    //This always gives a valid value because we verify weight and height before
    public void calculateBMI() {
        bmi = (int) Math.round(gewicht / (lengte * lengte));
    }
}
