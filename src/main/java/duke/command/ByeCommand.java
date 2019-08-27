package duke.command;

import duke.functionality.Storage;
import duke.functionality.TaskList;
import duke.functionality.Ui;

/**
 * Represents the command that will bid Duke goodbye. The command fed into a {@code ByeCommand} object is assumed to
 * be valid. The command is valid if and only if it is of the form "bye".
 */
public class ByeCommand extends Command {

    /**
     * Instantiates a {@code ByeCommand} object.
     * @param byeCommand the command that indicates the action of bidding goodbye to Duke
     */
    public ByeCommand(String byeCommand) {
        super(byeCommand);
    }

    /**
     * Executes the command fed into {@code ByeCommand} object. This will terminate the Duke program and Duke will bid
     * fare well to the user while saving the tasks in the task list onto a text file.
     * @param taskList the {@code TaskList} object in Duke
     * @param ui the {@code Ui} object in Duke
     * @param storage the {@code Storage} object in Duke
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.printByeMessage();
        storage.save(taskList.obtainList());
    }
}
