public class Deadline extends Task {
    private FormattedDate deadline;

    Deadline(String taskName, FormattedDate deadline) {
        super(taskName);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        String sym = this.isDone ? "\u2713" : "\u2717";
        return "[D]" + "[" + sym + "] " + this.taskName + " (by: " + this.deadline + ")";
    }
}
