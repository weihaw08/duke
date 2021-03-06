package duke.command;

import duke.statistics.WeeklyStatistics;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Represents the command that instructs Duke to print out the current tasks that are inside the task list.
 */
public class ListCommand extends Command {

    /**
     * Executes the command represented by the {@code ListCommand} object.
     *
     * @param taskList the {@code TaskList} object in Duke
     * @param ui       the {@code Ui} object in Duke
     * @param storage  the {@code Storage} object in Duke
     * @return a string representing the tasks inside taskList
     */
    public String execute(TaskList taskList, WeeklyStatistics stats, Ui ui, Storage storage) {
        StringBuilder msg;
        int size = taskList.size();
        if (size <= 5) {
            msg = new StringBuilder("<(^.^<) You must be quite free! You have " + size + " task(s)!\n");
        } else {
            msg = new StringBuilder("O.O\" You are quite busy! You have " + size + " task(s)!\n");
        }
        for (int i = 1; i <= size; i++) {
            msg.append(i).append(". ").append(taskList.retrieveTask(i)).append("\n");
        }
        return msg.toString();
    }
}
