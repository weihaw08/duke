package duke.command;

import duke.functionality.Storage;
import duke.functionality.TaskList;
import duke.functionality.Ui;

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
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        StringBuilder msg;
        int size = taskList.size();
        if (size == 0) {
            msg = new StringBuilder("HOORAY!! FREE SPIRIT!! You have no tasks!!\n");
        } else if (size <= 5) {
            String pluralOrNot = (size == 1) ? "task" : "tasks";
            msg = new StringBuilder("<(^.^<) You must be quite free! You only have " + size + " "
                + pluralOrNot + "!\n");
        } else if (size <= 10) {
            msg = new StringBuilder("O.O\" You are quite busy! You have " + size + " tasks!\n");
        } else {
            msg = new StringBuilder("T_T Please don't be stressed over your " + size + " tasks!\n");
        }
        for (int i = 1; i <= size; i++) {
            assert taskList.size() != 0;
            msg.append(i).append(". ").append(taskList.retrieveTask(i)).append("\n");
        }
        return msg.toString();
    }
}
