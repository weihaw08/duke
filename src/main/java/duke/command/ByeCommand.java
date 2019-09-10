package duke.command;

import duke.model.TaskList;
import duke.statistics.WeeklyStatistics;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Represents the command that will bid Duke goodbye.
 */
public class ByeCommand extends Command {

    /**
     * Executes the command fed into {@code ByeCommand} object. This will terminate the Duke program and Duke will bid
     * fare well to the user while saving the tasks and statistics into a text file.
     *
     * @param taskList the {@code TaskList} object in Duke
     * @param ui       the {@code Ui} object in Duke
     * @param storage  the {@code Storage} object in Duke
     * @return a string representing the bye message of Duke
     */
    public String execute(TaskList taskList, WeeklyStatistics stats, Ui ui, Storage storage) {
        storage.saveTasks(taskList);
        storage.saveStatistics(stats);
        return ui.giveBye();
    }
}
