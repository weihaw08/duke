package duke.exception;

public class InvalidTimeAndDateException extends Exception {
    private static final String MESSAGE = "☹ OOPS!!! You are breaking the space-time continuum!";

    public InvalidTimeAndDateException(String errorInput) {
        super(errorInput);
    }

    @Override
    public String toString() {
        return MESSAGE;
    }

    @Override
    public String getMessage() {
        return "The date(s) you entered could be incorrect: \n     " + super.getMessage();
    }
}
