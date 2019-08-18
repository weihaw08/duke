public class ToDo extends Task {

    ToDo(String toDoName) {
        super(toDoName);
    }

    @Override
    public String toString() {
        String sym = this.isDone ? "\u2713" : "\u2717";
        return "[T]" + "[" + sym + "] " + this.taskName;
    }
}
