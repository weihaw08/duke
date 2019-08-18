public abstract class Task {
    String taskName;
    boolean isDone;

    Task(String taskName) {
        this.taskName = taskName;
        isDone = false;
    }

    void markAsDone() {
        this.isDone = true;
    }

    public abstract String toString();
}
