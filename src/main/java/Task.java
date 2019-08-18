public abstract class Task {
    String taskName;
    boolean isDone;

    Task(String taskName) {
        this.taskName = taskName;
        isDone = false;
    }

    void markAsDone() {
        this.isDone = true;
        System.out.println("     Nice! I've marked this task as done:");
        System.out.println("     " + this.toString());
    }

    public abstract String toString();
}
