package be.ucll.labo6.bib;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Period;

public class Lid {

    private String voornaam, naam;
    private LocalDate geboortedatum;
    private int age;

    public Lid(String voornaam, String naam, int birthyear, int birthmonth, int birthday) {
        setNaam(naam);
        setVoornaam(voornaam);
        setGeboortedatum(birthyear, birthmonth, birthday);
        calculateAge();
    }

    public void setNaam(String naam) {
        if (naam == null || naam.trim().isEmpty()) {
            throw new IllegalArgumentException("Naam mag niet leeg zijn!");
        }
        this.naam = naam;
    }

    public void setVoornaam(String voornaam) {
        if (voornaam == null || voornaam.trim().isEmpty()) {
            throw new IllegalArgumentException("Voornaam mag niet leeg zijn!");
        }
        this.voornaam = voornaam;
    }

    public void setGeboortedatum(int birthyear, int birthmonth, int birthday) {
        try {
            LocalDate now = LocalDate.now();
            LocalDate birthdate = LocalDate.of(birthyear, birthmonth, birthday);

            geboortedatum = birthdate;
            if (now.compareTo(birthdate) < 0) {
                throw new IllegalArgumentException("Dude, are you from the future!?");
            }

        } catch (DateTimeException e) {
            System.out.println("Invalid birthdate!");
            e.printStackTrace();
        } catch (NullPointerException e) {
            System.out.println("Error");
        }
    }

    public void calculateAge() {
        LocalDate now = LocalDate.now();

        Period diff = Period.between(geboortedatum, now);
        age = diff.getYears();
    }

    @Override
    public String toString() {
        return voornaam + " " +
                naam + " (" +
                age + ")";
    }
}
