package duke.command;

import duke.statistics.WeeklyStatistics;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Represents a command that informs Duke that a particular task has been completed.
 */
public class DoneCommand extends Command {
    private int indexToComplete;

    /**
     * Instantiates a {@code DoneCommand} object.
     *
     * @param indexToComplete the index of the task to be marked as done
     */
    public DoneCommand(int indexToComplete) {
        this.indexToComplete = indexToComplete;
    }

    /**
     * Executes the done command that the {@code DoneCommand} object represents. The {@code Task} object at the index
     * given will be marked as completed, if such an object is present.
     *
     * @param taskList the {@code TaskList} object in Duke
     * @param ui       the {@code Ui} object in Duke
     * @param storage  the {@code Storage} object in Duke
     * @return a string representing the information of the task that has been marked as done or a string representing
     *     the exception encountered
     */
    public String execute(TaskList taskList, WeeklyStatistics stats, Ui ui, Storage storage) {
        try {
            Task completedTask = taskList.retrieveTask(indexToComplete);
            assert completedTask != null;
            completedTask.markAsDone();
            stats.addLatestCompletedTask();
            return ui.getTaskModificationNotice(taskList.size(), completedTask, "done");
        } catch (IndexOutOfBoundsException e) {
            if (taskList.size() == 0) {
                return ui.getEmptyListMessage();
            } else {
                return ui.getIndexNotFoundMessage(indexToComplete);
            }
        }
    }
}
