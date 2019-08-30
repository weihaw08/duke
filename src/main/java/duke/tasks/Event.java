package duke.tasks;

/**
 * Represents a task that has a start date and an end date. A {@code Event} task object is a {@code Task} object
 * with a task name, start date and end date.
 */
public class Event extends Task {
    private FormattedDate start;
    private FormattedDate end;

    /**
     * Instantiates an {@code Event} object.
     *
     * @param taskName the name of the task
     * @param start    the start date of the task
     * @param end      the end date of the task
     */
    public Event(String taskName, FormattedDate start, FormattedDate end) {
        super(taskName);
        this.start = start;
        this.end = end;
    }

    /**
     * Instantiates an {@code Event} object. The user is allowed to set if the task has been completed or not.
     *
     * @param taskName the name of the task
     * @param isDone   the state of the task
     * @param start    the start date of the task
     * @param end      the end date of the task
     */
    public Event(String taskName, boolean isDone, FormattedDate start, FormattedDate end) {
        super(taskName, isDone);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        String sym = this.isDone ? "✓" : "✗";
        return "[E]" + "[" + sym + "] " + this.taskName + " (at: " + this.start + " - " + this.end + ")";
    }

    /**
     * Creates a text version of the {@code Event} task. This text version is stored in a text file.
     *
     * @return a string that represents the state, the name, the start date and the end date of the
     * {@code Deadline} task.
     */
    @Override
    public String convertToText() {
        return "E" + " ~ " + this.isDone + " ~ " + this.taskName + " ~ " + this.start + " - " + this.end;
    }
}
