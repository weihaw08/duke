public abstract class Task {
    String taskName;
    boolean isDone;

    Task(String taskName) {
        this.taskName = taskName;
        isDone = false;
    }

    Task(String taskName, boolean isDone) {
        this.taskName = taskName;
        this.isDone = isDone;
    }

    void markAsDone() {
        this.isDone = true;
    }

    public abstract String toString();

    protected abstract String convertToText();
}
