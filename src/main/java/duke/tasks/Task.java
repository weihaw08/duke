package duke.tasks;

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

    public void markAsDone() {
        this.isDone = true;
    }

    public abstract String toString();

    public abstract String convertToText();
}
