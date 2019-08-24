public class Event extends Task{
    private FormattedDate start;
    private FormattedDate end;

    Event(String taskName, FormattedDate start, FormattedDate end) {
        super(taskName);
        this.start = start;
        this.end = end;
    }

    Event(String taskName, boolean isDone, FormattedDate start, FormattedDate end) {
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
    protected String convertToText() {
        return "E" + " ~ " + this.isDone + " ~ " + this.taskName + " ~ " + this.start + " - " + this.end;
    }
}
