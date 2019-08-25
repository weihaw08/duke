package duke.command;

import duke.functionality.TaskList;
import duke.functionality.Ui;
import duke.functionality.Storage;
import duke.exception.EmptyListException;
import duke.exception.IndexNotFoundException;
import duke.tasks.Task;

public class DoneCommand extends Command {

    public DoneCommand(String doneCommand) {
        super(doneCommand);
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) {
        String[] tokens = this.command.split(" ");
        int index = Integer.valueOf(tokens[1]);
        ui.printBorder();
        try {
            Task completedTask = taskList.retrieveTask(index);
            completedTask.markAsDone();
            ui.printTaskModification(taskList.size(), completedTask, "done");
        } catch (EmptyListException e) {
            ui.printMessage(e.toString());
        } catch (IndexNotFoundException e) {
            ui.printMessage(e.toString());
        } finally {
            ui.printBorder();
        }
    }
}
