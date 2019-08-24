public class ToDo extends Task {

    ToDo(String taskName) {
        super(taskName);
    }

    ToDo(String taskName, boolean isDone) {
        super(taskName, isDone);
    }

    @Override
    public String toString() {
        String sym = this.isDone ? "\u2713" : "\u2717";
        return "[T]" + "[" + sym + "] " + this.taskName;
    }

    @Override
    protected String convertToText() {
        return "T" + " ~ " + this.isDone + " ~ " + this.taskName;
    }
}
