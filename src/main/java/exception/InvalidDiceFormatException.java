package exception;

public class InvalidDiceFormatException extends RuntimeException {
    public InvalidDiceFormatException(String message) {
        super(message);
    }
}