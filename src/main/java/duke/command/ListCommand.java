package duke.command;

import duke.exception.EmptyListException;
import duke.exception.IndexNotFoundException;
import duke.functionality.Storage;
import duke.functionality.TaskList;
import duke.functionality.Ui;

/**
 * Represents the command that instructs Duke to print out the current tasks that are inside the task list. The command
 * fed into a {@code ListCommand} object is assumed to be valid. A command is valid if and only if it is of the form
 * "list".
 */
public class ListCommand extends Command {

    /**
     * Instantiates a {@code listCommand} object.
     * @param listCommand a command that indicates the action of wanting the list from Duke.
     */
    public ListCommand(String listCommand) {
        super(listCommand);
    }

    /**
     * Executes the command that is fed into the {@code ListCommand} object. This will prompt Duke to print out all of
     * the current tasks in the {@code TaskList} object.
     * @param taskList the {@code TaskList} object in Duke
     * @param ui the {@code Ui} object in Duke
     * @param storage the {@code Storage} object in Duke
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.printBorder();
        String msg;
        int size = taskList.size();
        if (size <= 5) {
            String pluralOrNot = (size == 1) ? "task" : "tasks";
            msg = "<(^.^<) You must be quite free! You only have " + size + " " + pluralOrNot + "!";
        } else if (size <= 10) {
            msg = "O.O\" You are quite busy! You have " + size + " tasks!";
        } else {
            msg = "T_T Please don't be stressed over your " + size + " tasks!";
        }
        ui.printMessage(msg);
        try {
            for (int i = 1; i <= size; i++) {
                ui.printMessage(i + ". " + taskList.retrieveTask(i));
            }
        } catch (EmptyListException e) {
            ui.printMessage(e.toString());
        } catch (IndexNotFoundException e) {
            ui.printMessage(e.toString());
        } finally {
            ui.printBorder();
        }
    }
}
