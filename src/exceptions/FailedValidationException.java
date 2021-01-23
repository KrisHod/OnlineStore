package exceptions;

public class FailedValidationException extends Exception{
    public FailedValidationException (String message){
        super(message);
    }
}
