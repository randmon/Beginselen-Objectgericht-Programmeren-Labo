package be.ucll.labo6.userInput;

public class EmptyFieldException extends Exception{
    public EmptyFieldException(String errorMessage) {
        super(errorMessage);
    }

    public EmptyFieldException() {

    }
}
