public class Event extends Task{
    private FormattedDate start;
    private FormattedDate end;

    Event(String taskName, FormattedDate start, FormattedDate end) {
        super(taskName);
        this.start = start;
        this.end = end;
    }

    Event(String taskName, boolean isDone, String timing) {
        super(taskName, isDone);
        this.timing = timing;
    }

    @Override
    public String toString() {
        String sym = this.isDone ? "\u2713" : "\u2717";
        return "[E]" + "[" + sym + "] " + this.taskName + " (at: " + this.start + " - " + this.end + ")";
    }

    @Override
    protected String convertToText() {
        return "E" + " ~ " + this.isDone + " ~ " + this.taskName + " ~ " + this.timing;
    }
}
