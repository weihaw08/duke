public class Deadline extends Task {
    private String deadline;

    Deadline(String taskName, String deadline) {
        super(taskName);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        String sym = this.isDone ? "\u2713" : "\u2717";
        return "[D]" + "[" + sym + "] " + this.taskName + " (by: " + this.deadline + ")";
    }
}
