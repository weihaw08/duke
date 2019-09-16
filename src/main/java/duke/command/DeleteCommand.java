package duke.command;

import duke.model.TaskList;
import duke.statistics.WeeklyStatistics;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.ui.Ui;

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
    public String execute(TaskList taskList, WeeklyStatistics stats, Ui ui, Storage storage) {
        try {
            Task toBeRemoved = taskList.retrieveTask(indexToRemove);
            assert toBeRemoved != null;
            taskList.deleteTask(indexToRemove);
            if (!toBeRemoved.isDone()) {
                stats.reduceLatestIncompleteTask();
            }
            return ui.getTaskModificationNotice(taskList.size(), toBeRemoved, "delete");
        } catch (IndexOutOfBoundsException e) {
            if (taskList.size() == 0) {
                return ui.getEmptyListMessage();
            } else {
                return ui.getIndexNotFoundMessage(indexToRemove);
            }
        }
    }
}
