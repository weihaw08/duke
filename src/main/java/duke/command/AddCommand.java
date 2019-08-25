package duke.command;

import java.text.ParseException;
import duke.functionality.TaskList;
import duke.functionality.Ui;
import duke.functionality.Storage;
import duke.exception.InvalidTimeAndDateException;
import duke.tasks.FormattedDate;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;

public class AddCommand extends Command {

    public AddCommand(String addCommand) {
        super(addCommand);
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) {
        String[] tokens = this.command.split(" ");
        Task newTask = null;
        ui.printBorder();
        try {
            if (tokens[0].equals("todo")) {
                String todoName = this.command.replace("todo ", "");
                newTask = new ToDo(todoName);
            } else if (tokens[0].equals("deadline")) {
                String deadlineName = this.command.replace("deadline ", "");
                String[] splitDeadline = deadlineName.split(" /by ");
                FormattedDate byTime = new FormattedDate(splitDeadline[1]);
                newTask = new Deadline(splitDeadline[0], byTime);
            } else {
                String eventName = this.command.replace("event ", "");
                String[] splitEvent = eventName.split(" /at ");
                String[] startAndEnd = splitEvent[1].split(" - ");
                FormattedDate start = new FormattedDate(startAndEnd[0]);
                FormattedDate end = new FormattedDate(startAndEnd[1]);
                newTask = new Event(splitEvent[0], start, end);
            }
        } catch (ParseException e) {
            ui.printMessage("T.T!!! Please enter the dates like this:");
            ui.printMessage("\"dd/mm/yyyy hhmm\"!");
        } catch (InvalidTimeAndDateException e) {
            ui.printMessage(e.toString());
        } finally {
            if (newTask != null) {
                taskList.addTask(newTask);
                ui.printTaskModification(taskList.size(), newTask, "add");
            }
            ui.printBorder();
        }
    }
}
