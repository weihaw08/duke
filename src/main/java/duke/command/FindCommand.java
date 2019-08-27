package duke.command;

import duke.exception.EmptyListException;
import duke.exception.IndexNotFoundException;
import duke.functionality.Storage;
import duke.functionality.TaskList;
import duke.functionality.Ui;
import duke.tasks.Task;

/**
 * Represents a command to instruct Duke to find tasks that contain specific keyword(s). The command fed into
 * {@code FindCommand} object is assumed to be valid. The command is valid if and only if it is of the form
 * "find ~keyword(s)~".
 */
public class FindCommand extends Command {

    /**
     * Instantiates a {@code FindCommand} object.
     * @param findCommand a command that indicates the action of asking Duke to look for tasks with specific keyword(s)
     */
    public FindCommand(String findCommand) {
        super(findCommand);
    }

    /**
     * Executes the find command inside the {@code FindCommand} object. Duke will perform a linear search through the
     * {@code TaskList} object to look for the {@code Task} objects that match the specific keyword(s).
     * @param taskList the {@code TaskList} object in Duke
     * @param ui the {@code Ui} object in Duke
     * @param storage the {@code Storage} object in Duke
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        boolean isFound = false;
        String[] tokens = this.command.split(" ");
        int count = 0;
        ui.printBorder();
        try {
            for (int i = 1; i <= taskList.size(); i++) {
                Task task = taskList.retrieveTask(i);
                if (task.getTaskName().contains(tokens[1])) {
                    if (!isFound) {
                        ui.printMessage("It's a match!");
                        isFound = true;
                    }
                    ui.printMessage(i + ". " + task.toString());
                }
            }
            if (!isFound) {
                ui.printMessage("UwU\" No matches found. Please try again.");
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
