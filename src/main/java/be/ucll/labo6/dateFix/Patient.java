package be.ucll.labo6.dateFix;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Patient {

    private LocalDate birthdate;
    private final DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public Patient(String bdString) {
        try {
            setBirthdate(bdString);
        } catch (DateTimeParseException e) {
            System.out.println("Date format invalid!");
        } catch (IllegalArgumentException e) {
            e.getMessage();
        }
    }

    public void setBirthdate(String bdString) throws DateTimeParseException, IllegalArgumentException{
        LocalDate temp;
        checkNotEmpty(bdString);
        temp = checkFormat(bdString);
        checkNotFuture(temp);
        birthdate = temp; //Set birthdate if everything is alright
    }

    //Throws IllegalArgumentException if date is an empty String
    private void checkNotEmpty(String bdString) throws IllegalArgumentException {
        if (bdString == null || bdString.trim().isEmpty()) {
            throw new IllegalArgumentException("Date is empty!");
        }
    }

    //Attempts to parse the String into a LocalDate according to our format
    //If it succeeds it gives back the date
    private LocalDate checkFormat(String bdString) throws DateTimeParseException{
        return LocalDate.parse(bdString, dtFormatter);
    }

    //Throws IllegalArgumentException if date is in the future
    private void checkNotFuture(LocalDate temp) throws IllegalArgumentException {
        if (LocalDate.now().compareTo(temp) < 0) {
            throw new IllegalArgumentException("Date is in the future!");
        }
    }

    //Give back the birthdate in our String format so it can be printed
    public String getBirthdateString() {
        return birthdate.format(dtFormatter);
    }
}
