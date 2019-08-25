package duke.command;

import duke.functionality.TaskList;
import duke.functionality.Ui;
import duke.functionality.Storage;
import duke.exception.EmptyListException;
import duke.exception.IndexNotFoundException;
import duke.tasks.Task;

public class DeleteCommand extends Command {

    public DeleteCommand(String deleteCommand) {
        super(deleteCommand);
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) {
        String[] tokens = this.command.split(" ");
        int indexToRemove = Integer.parseInt(tokens[1]);
        ui.printBorder();
        try {
            Task toBeRemoved = taskList.retrieveTask(indexToRemove);
            taskList.deleteTask(indexToRemove);
            ui.printTaskModification(taskList.size(), toBeRemoved, "delete");
        } catch (EmptyListException e) {
            ui.printMessage(e.toString());
        } catch (IndexNotFoundException e) {
            ui.printMessage(e.toString());
        } finally {
            ui.printBorder();
        }
    }
}
