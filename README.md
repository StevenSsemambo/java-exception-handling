# Custom Exception Handling System in Java

## Video walkthrough

**Video URL:** `PASTE_YOUR_PUBLIC_VIDEO_LINK_HERE`

## Run it

Requires JDK 11 or newer.

```bash
mkdir -p out
javac -d out src/exceptions/*.java src/app/*.java
java -cp out app.Main
```

Type text to save it. Type `quit` to exit. Try empty input, `bad<>chars`, and valid text.
The simulated database fails about 30% of the time, so keep entering valid text to see `DatabaseConnectionException` handled.

## Design

```
AppException (base, extends Exception)
 |- InvalidInputException          bad user input
 |- DatabaseConnectionException    simulated connection failure
```

| File | Role |
|---|---|
| `exceptions/AppException.java` | Common parent so callers can catch both with one type if needed |
| `exceptions/InvalidInputException.java` | Carries the rejected value |
| `exceptions/DatabaseConnectionException.java` | Carries the attempt number |
| `app/SimulatedDatabase.java` | `connect()` randomly throws based on a failure rate |
| `app/InputProcessor.java` | `process()` validates, connects, then saves. Validation runs first so no connection is opened for bad data |
| `app/Main.java` | Input loop with separate `catch` blocks and a `finally` that runs every time |

Validation rules: not null, not empty or blank, at most 50 characters, only letters, digits, spaces and `_ . @ -`.

The loop never exits on an exception, so the program keeps running after every error.
