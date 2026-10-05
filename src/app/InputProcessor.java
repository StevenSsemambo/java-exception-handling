package app;

import exceptions.DatabaseConnectionException;
import exceptions.InvalidInputException;

public class InputProcessor {
    private static final int MAX_LENGTH = 50;
    private final SimulatedDatabase database;

    public InputProcessor(SimulatedDatabase database) {
        this.database = database;
    }

    /**
     * Validates the input, connects to the database, then stores the input.
     * Validation happens first so we never open a connection for bad data.
     */
    public void process(String input) throws InvalidInputException, DatabaseConnectionException {
        validate(input);
        database.connect();
        database.save(input.trim());
    }

    private void validate(String input) throws InvalidInputException {
        if (input == null) {
            throw new InvalidInputException("Input must not be null.", null);
        }
        if (input.trim().isEmpty()) {
            throw new InvalidInputException("Input must not be empty or blank.", input);
        }
        if (input.trim().length() > MAX_LENGTH) {
            throw new InvalidInputException(
                    "Input is too long (max " + MAX_LENGTH + " characters).", input);
        }
        if (!input.trim().matches("[A-Za-z0-9 _.@-]+")) {
            throw new InvalidInputException(
                    "Input contains invalid characters. Use letters, digits, spaces and _ . @ - only.",
                    input);
        }
    }
}
