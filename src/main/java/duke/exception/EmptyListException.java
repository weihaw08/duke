package duke.exception;

public class EmptyListException extends Exception {
    private static final String MESSAGE = "☹ OOPS!!! The list is currently empty!";

    @Override
    public String toString() {
        return MESSAGE;
    }
}
