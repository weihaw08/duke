package duke.tasks;

public class Event extends Task {
    private FormattedDate start;
    private FormattedDate end;

    /**
     * Instantiates an {@code Event} object.
     * @param taskName the name of the event
     * @param start the start date of the event
     * @param end the end date of the event
     */
    public Event(String taskName, FormattedDate start, FormattedDate end) {
        super(taskName);
        this.start = start;
        this.end = end;
    }

    /**
     * Instantiates an {@code Event} object. The user is able to set the state of the event.
     * @param taskName the name of the event
     * @param isDone the state of the event
     * @param start the start date of the event
     * @param end the end date of the event
     */
    public Event(String taskName, boolean isDone, FormattedDate start, FormattedDate end) {
        super(taskName, isDone);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        String sym = this.isDone ? "✓" : "✗";
        return "[E]" + "[" + sym + "] " + this.taskName + " (at: " + this.start + " - " + this.end + ")";
    }

    @Override
    public String convertToText() {
        return "E" + " ~ " + this.isDone + " ~ " + this.taskName + " ~ " + this.start + " - " + this.end;
    }
}
