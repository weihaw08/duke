public class Deadline extends Task {
    private FormattedDate deadline;

    Deadline(String taskName, FormattedDate deadline) {
        super(taskName);
        this.deadline = deadline;
    }

    Deadline(String taskName, boolean isDone, String deadline) {
        super(taskName, isDone);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        String sym = this.isDone ? "\u2713" : "\u2717";
        return "[D]" + "[" + sym + "] " + this.taskName + " (by: " + this.deadline + ")";
    }

    @Override
    protected String convertToText() {
        return "D" + " ~ " + this.isDone + " ~ " + this.taskName + " ~ " + this.deadline;
    }
}
