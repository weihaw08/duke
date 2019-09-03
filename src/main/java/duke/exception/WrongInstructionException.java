package duke.exception;

public class WrongInstructionException extends Exception {
    private static final String ERROR_MESSAGE = "o_O OOPS!!! I'm sorry, but I don't know what that means :-(";
    private String message;

    public WrongInstructionException(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return ERROR_MESSAGE;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
