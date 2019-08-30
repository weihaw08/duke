package duke.command;

import duke.functionality.Storage;
import duke.functionality.TaskList;
import duke.functionality.Ui;

/**
 * Represents the command that instructs Duke to print out the current tasks that are inside the task list.
 */
public class ListCommand extends Command {

    /**
     * Executes the list command that the {@code ListCommand} object represents. This will prompt Duke to print out all
     * of the current tasks in the {@code TaskList} object.
     *
     * @param taskList the {@code TaskList} object in Duke
     * @param ui       the {@code Ui} object in Duke
     * @param storage  the {@code Storage} object in Duke
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        String msg;
        int size = taskList.size();
        if (size == 0) {
            msg = "HOORAY!! FREE SPIRIT!! You have no tasks!!";
        } else if (size <= 5) {
            String pluralOrNot = (size == 1) ? "task" : "tasks";
            msg = "<(^.^<) You must be quite free! You only have " + size + " " + pluralOrNot + "!";
        } else if (size <= 10) {
            msg = "O.O\" You are quite busy! You have " + size + " tasks!";
        } else {
            msg = "T_T Please don't be stressed over your " + size + " tasks!";
        }
        ui.printMessage(msg);
        for (int i = 1; i <= size; i++) {
            ui.printMessage(i + ". " + taskList.retrieveTask(i));
        }
    }
}
