package duke.command;

import duke.exception.EmptyListException;
import duke.exception.IndexNotFoundException;
import duke.functionality.Storage;
import duke.functionality.TaskList;
import duke.functionality.Ui;
import duke.tasks.Task;

/**
 * Represents the command that will instruct Duke to delete a {@code Task} object from the {@code TaskList} object. The
 * command fed into a {@code DeleteCommand} object is assumed to be valid. The command is valid if and only if it is of
 * the form "delete ~task number~".
 */
public class DeleteCommand extends Command {

    /**
     * Instantiates a {@code DeleteCommand} object.
     * @param deleteCommand a command that indicates the action of deleting a task from the task list.
     */
    public DeleteCommand(String deleteCommand) {
        super(deleteCommand);
    }

    /**
     * Executes the command fed into {@code DeleteCommand} object. A {@code Task} object will be deleted from the
     * {@code TaskList} object if and only if there is a task at the given index and the list is not empty.
     * @param taskList the {@code TaskList} object in Duke
     * @param ui the {@code Ui} object in Duke
     * @param storage the {@code Storage} object in Duke
     */
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
