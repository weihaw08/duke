package duke.tasks;

/**
 * Represents a task that one intends to do. A {@code ToDo} object is a {@code Task} object that contains a task name.
 */
public class ToDo extends Task {

    /**
     * Instantiates a {@code ToDo} object.
     *
     * @param taskName the name of the task
     */
    public ToDo(String taskName) {
        super(taskName);
    }

    /**
     * Instantiates a {@code ToDo} object. The user can state if the task has been completed or not.
     *
     * @param taskName the name of the task
     * @param isDone   the state of the task
     */
    public ToDo(String taskName, boolean isDone) {
        super(taskName, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Creates a text version of the {@code ToDo} task. This text version is stored in a text file.
     *
     * @return a string that represents the state and the name of the {@code ToDo} task.
     */
    @Override
    public String convertToText() {
        return "T" + " ~ " + this.isDone + " ~ " + this.taskName;
    }
}
