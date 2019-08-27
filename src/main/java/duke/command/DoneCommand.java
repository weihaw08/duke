package duke.command;

import duke.exception.EmptyListException;
import duke.exception.IndexNotFoundException;
import duke.functionality.Storage;
import duke.functionality.TaskList;
import duke.functionality.Ui;
import duke.tasks.Task;

public class DoneCommand extends Command {

    public DoneCommand(String doneCommand) {
        super(doneCommand);
    }

    /**
     * Executes the done command in the {@code DoneCommand} object.
     * @param taskList the {@code TaskList} object in Duke
     * @param ui the {@code Ui} object in Duke
     * @param storage the {@code Storage} object in Duke
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        String[] tokens = this.command.split(" ");
        int index = Integer.parseInt(tokens[1]);
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
