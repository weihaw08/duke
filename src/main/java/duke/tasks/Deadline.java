package duke.tasks;

public class Deadline extends Task {
    private FormattedDate deadline;

    public Deadline(String taskName, FormattedDate deadline) {
        super(taskName);
        this.deadline = deadline;
    }

    public Deadline(String taskName, boolean isDone, FormattedDate deadline) {
        super(taskName, isDone);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        String sym = this.isDone ? "✓" : "✗";
        return "[D]" + "[" + sym + "] " + this.taskName + " (by: " + this.deadline + ")";
    }

    @Override
    public String convertToText() {
        return "D" + " ~ " + this.isDone + " ~ " + this.taskName + " ~ " + this.deadline;
    }
}
