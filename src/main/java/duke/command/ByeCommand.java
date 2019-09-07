package duke.command;

import duke.model.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Represents the command that will bid Duke goodbye.
 */
public class ByeCommand extends Command {

    /**
     * Executes the command fed into {@code ByeCommand} object. This will terminate the Duke program and Duke will bid
     * fare well to the user while saving the tasks in the task list onto a text file.
     *
     * @param taskList the {@code TaskList} object in Duke
     * @param ui       the {@code Ui} object in Duke
     * @param storage  the {@code Storage} object in Duke
     * @return a string representing the bye message of Duke
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        storage.save(taskList.obtainList());
        return ui.giveBye();
    }
}
