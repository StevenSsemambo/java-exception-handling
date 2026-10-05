package exceptions;

/** Thrown when user input does not meet validation criteria. */
public class InvalidInputException extends AppException {
    private final String rejectedValue;

    public InvalidInputException(String message, String rejectedValue) {
        super(message);
        this.rejectedValue = rejectedValue;
    }

    public String getRejectedValue() {
        return rejectedValue;
    }
}
