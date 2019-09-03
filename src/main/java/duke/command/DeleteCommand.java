package duke.command;

import duke.functionality.Storage;
import duke.functionality.TaskList;
import duke.functionality.Ui;
import duke.tasks.Task;

/**
 * Represents the command that will instruct Duke to delete a {@code Task} object from the {@code TaskList} object.
 */
public class DeleteCommand extends Command {
    private int indexToRemove;

    /**
     * Instantiates a {@code DeleteCommand} object.
     *
     * @param indexToRemove the index of the task to be deleted
     */
    public DeleteCommand(int indexToRemove) {
        this.indexToRemove = indexToRemove;
    }

    /**
     * Executes the delete command that the {@code DeleteCommand} object represents. A {@code Task} object will be
     * deleted from the {@code TaskList} object, if an object is present at that index.
     *
     * @param taskList the {@code TaskList} object in Duke
     * @param ui       the {@code Ui} object in Duke
     * @param storage  the {@code Storage} object in Duke
     * @return a string representing the information of the task that has been deleted or a string representing an
     *     exception encountered
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            Task toBeRemoved = taskList.retrieveTask(indexToRemove);
            taskList.deleteTask(indexToRemove);
            return ui.printTaskModification(taskList.size(), toBeRemoved, "delete");
        } catch (IndexOutOfBoundsException e) {
            if (taskList.size() == 0) {
                return ui.getEmptyListMessage();
            } else {
                return ui.getIndexNotFoundMessage(indexToRemove);
            }
        }
    }
}
