public class EmptyDescriptionException extends Exception{
    private String taskType;

    EmptyDescriptionException(String taskType) {
        this.taskType = taskType;
    }

    @Override
    public String toString() {
        return "☹ OOPS!!! The description of a " + this.taskType + " cannot be empty.";
    }
}
