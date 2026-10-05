package exceptions;

/** Thrown when the simulated database connection fails. */
public class DatabaseConnectionException extends AppException {
    private final int attempt;

    public DatabaseConnectionException(String message, int attempt) {
        super(message);
        this.attempt = attempt;
    }

    public int getAttempt() {
        return attempt;
    }
}
