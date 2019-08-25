package duke.exception;

public class WrongInstructionException extends Exception {
    private static final String MESSAGE = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";

    @Override
    public String toString() {
        return MESSAGE;
    }
}
