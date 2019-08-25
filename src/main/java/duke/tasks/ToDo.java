package duke.tasks;

public class ToDo extends Task {

    public ToDo(String taskName) {
        super(taskName);
    }

    public ToDo(String taskName, boolean isDone) {
        super(taskName, isDone);
    }

    @Override
    public String toString() {
        String sym = this.isDone ? "\u2713" : "\u2717";
        return "[T]" + "[" + sym + "] " + this.taskName;
    }

    @Override
    public String convertToText() {
        return "T" + " ~ " + this.isDone + " ~ " + this.taskName;
    }
}
