public class Task {
    private String taskName;
    private boolean isDone;

    Task(String taskName) {
        this.taskName = taskName;
        isDone = false;
    }

    void markAsDone() {
        this.isDone = true;
        System.out.println("     Nice! I've marked this task as done:");
        System.out.println("     " + this);
    }

    @Override
    public String toString() {
        String sym = isDone ? "✓" : "✗";
        return "[" + sym + "] " + taskName;
    }
}
