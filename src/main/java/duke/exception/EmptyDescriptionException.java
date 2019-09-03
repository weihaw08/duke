package duke.exception;

public class EmptyDescriptionException extends Exception {
    private String taskType;

    public EmptyDescriptionException(String taskType) {
        this.taskType = taskType;
    }

    @Override
    public String toString() {
        return "o_O OOPS!!! The description of a " + this.taskType + " cannot be empty.";
    }
}
