package app;

import exceptions.DatabaseConnectionException;
import exceptions.InvalidInputException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // 30% of connection attempts fail, so you will see both exceptions in a normal run.
        InputProcessor processor = new InputProcessor(new SimulatedDatabase(0.3));
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Custom Exception Handling Demo ===");
        System.out.println("Type some text to save it, or 'quit' to exit.");

        while (true) {
            System.out.print("\n> ");
            if (!scanner.hasNextLine()) {
                break; // stdin closed
            }
            String input = scanner.nextLine();
            if ("quit".equalsIgnoreCase(input.trim())) {
                break;
            }

            try {
                processor.process(input);
                System.out.println("Success: input processed.");
            } catch (InvalidInputException e) {
                System.out.println("Invalid input: " + e.getMessage() + " Please try again.");
            } catch (DatabaseConnectionException e) {
                System.out.println("Database error (attempt #" + e.getAttempt() + "): "
                        + e.getMessage() + " Please try again in a moment.");
            } finally {
                // Runs after every attempt, success or failure.
                System.out.println("(attempt finished)");
            }
        }

        System.out.println("Goodbye!");
        scanner.close();
    }
}
