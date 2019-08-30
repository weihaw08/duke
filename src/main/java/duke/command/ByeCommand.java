package duke.command;

import duke.functionality.Storage;
import duke.functionality.TaskList;
import duke.functionality.Ui;

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
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.printByeMessage();
        storage.save(taskList.obtainList());
    }
}
