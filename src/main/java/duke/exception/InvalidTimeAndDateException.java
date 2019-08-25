package duke.exception;

public class InvalidTimeAndDateException extends Exception {
    private static final String MESSAGE = "☹ OOPS!!! You are breaking the space-time continuum!";

    @Override
    public String toString() {
        return MESSAGE;
    }
}
