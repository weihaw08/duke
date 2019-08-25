package duke.tasks;

public class Event extends Task {
    private FormattedDate start;
    private FormattedDate end;

    public Event(String taskName, FormattedDate start, FormattedDate end) {
        super(taskName);
        this.start = start;
        this.end = end;
    }

    public Event(String taskName, boolean isDone, FormattedDate start, FormattedDate end) {
        super(taskName, isDone);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        String sym = this.isDone ? "\u2713" : "\u2717";
        return "[E]" + "[" + sym + "] " + this.taskName + " (at: " + this.start + " - " + this.end + ")";
    }

    @Override
    public String convertToText() {
        return "E" + " ~ " + this.isDone + " ~ " + this.taskName + " ~ " + this.start + " - " + this.end;
    }
}
