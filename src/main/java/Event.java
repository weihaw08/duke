public class Event extends Task{
    private String timing;

    Event(String taskName, String timing) {
        super(taskName);
        this.timing = timing;
    }

    public String toString() {
        String sym = this.isDone ? "\u2713" : "\u2717";
        return "[E]" + "[" + sym + "] " + this.taskName + " (at: " + this.timing + ")";
    }

}
