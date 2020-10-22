package be.ucll.labo6.userInput;

public class FutureDateException extends Exception{
    public FutureDateException(String errorMessage) {
        super(errorMessage);
    }

    public FutureDateException() {

    }
}
