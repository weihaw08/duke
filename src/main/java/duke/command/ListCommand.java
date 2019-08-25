package duke.command;

import duke.functionality.TaskList;
import duke.functionality.Ui;
import duke.functionality.Storage;
import duke.exception.EmptyListException;
import duke.exception.IndexNotFoundException;

public class ListCommand extends Command {

    public ListCommand(String listCommand) {
        super(listCommand);
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.printBorder();
        try {
            ui.printMessage("T_T! These are the tasks for the busy you:");
            for (int i = 1; i <= taskList.size(); i++) {
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
