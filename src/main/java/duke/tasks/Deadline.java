package duke.tasks;

import java.time.LocalDateTime;

/**
 * Represents a task that has to be completed before a certain time. A {@code Deadline} object is a {@code Task}
 * object with a task name and a deadline.
 */
public class Deadline extends Task {
    private FormattedDateTime deadline;

    /**
     * Instantiates a {@code Deadline} object.
     *
     * @param taskName the name of the task
     * @param deadline the time in which the task is due
     */
    public Deadline(String taskName, FormattedDateTime deadline) {
        super(taskName);
        this.deadline = deadline;
    }

    /**
     * Instantiates a {@code Deadline} object. The user is allowed to set if the task has been completed or not.
     *
     * @param taskName the name of the task
     * @param isDone   the state of the task
     * @param deadline the time in which the task is due
     */
    public Deadline(String taskName, boolean isDone, FormattedDateTime deadline) {
        super(taskName, isDone);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline + ")";
    }

    /**
     * Creates a text version of the {@code Deadline} task. This text version is stored in a text file.
     *
     * @return a string that represents the state, the name and the deadline of the {@code Deadline} task.
     */
    @Override
    public String convertToText() {
        return "D" + " ~ " + this.isDone + " ~ " + this.taskName
            + " ~ " + this.deadline;
    }

    public LocalDateTime getDateTime() {
        return this.deadline.getDateAndTime();
    }
}
