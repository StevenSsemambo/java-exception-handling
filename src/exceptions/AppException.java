package exceptions;

/**
 * Root of the custom exception hierarchy. Both specific exceptions extend this,
 * so callers can catch either one individually or everything with AppException.
 */
public class AppException extends Exception {
    public AppException(String message) {
        super(message);
    }

    public AppException(String message, Throwable cause) {
        super(message, cause);
    }
}
