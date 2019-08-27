package duke.command;

import duke.exception.EmptyListException;
import duke.exception.IndexNotFoundException;
import duke.functionality.Storage;
import duke.functionality.TaskList;
import duke.functionality.Ui;
import duke.tasks.Task;

/**
 * Represents a command that informs Duke that a particular task has been completed. The command fed to a
 * {@code DoneCommand} object is assumed to be valid. The command is valid if and only if it is of the form
 * "done ~task number~".
 */
public class DoneCommand extends Command {

    /**
     * Instantiates a {@code DoneCommand} object.
     * @param doneCommand a command that indicates that a particular task is completed
     */
    public DoneCommand(String doneCommand) {
        super(doneCommand);
    }

    /**
     * Executes the command that has been fed into the {@code DoneCommand} object. The {@code Task} object at the index
     * given will be deleted if and only if there is a {@code Task} object in that index and the list is nonempty.
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
