package app;

import exceptions.DatabaseConnectionException;
import java.util.Random;

/** Pretends to be a database. Connection fails randomly (or on demand for testing). */
public class SimulatedDatabase {
    private final Random random = new Random();
    private final double failureRate;
    private int attempts = 0;

    public SimulatedDatabase(double failureRate) {
        this.failureRate = failureRate;
    }

    public void connect() throws DatabaseConnectionException {
        attempts++;
        if (random.nextDouble() < failureRate) {
            throw new DatabaseConnectionException(
                    "Could not reach the database server (simulated timeout).", attempts);
        }
    }

    public void save(String data) {
        System.out.println("  [DB] Saved record: \"" + data + "\"");
    }
}
