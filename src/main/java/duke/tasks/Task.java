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

    /**
     * Retrieves the task name of the {@code Task} object.
     *
     * @return the task name of the {@code Task} object
     */
    public String getTaskName() {
        return this.taskName;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String toString() {
        String sym = this.isDone ? "Done" : "Not Done";
        return "[" + sym + "] " + this.taskName;
    }

    public abstract String convertToText();
}
