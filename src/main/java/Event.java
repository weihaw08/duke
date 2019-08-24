public class Event extends Task{
    private String timing;

    Event(String taskName, String timing) {
        super(taskName);
        this.timing = timing;
    }

    Event(String taskName, boolean isDone, String timing) {
        super(taskName, isDone);
        this.timing = timing;
    }

    @Override
    public String toString() {
        String sym = this.isDone ? "\u2713" : "\u2717";
        return "[E]" + "[" + sym + "] " + this.taskName + " (at: " + this.timing + ")";
    }

    @Override
    protected String convertToText() {
        return "E" + " ~ " + this.isDone + " ~ " + this.taskName + " ~ " + this.timing;
    }
}
