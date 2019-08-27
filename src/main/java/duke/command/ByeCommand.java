package duke.command;

import duke.functionality.Storage;
import duke.functionality.TaskList;
import duke.functionality.Ui;

import java.io.IOException;


public class ByeCommand extends Command {

    public ByeCommand(String byeCommand) {
        super(byeCommand);
    }

    /**
     * Executes the bye command in the {@code ByeCommand} object.
     * @param taskList the {@code TaskList} object in Duke
     * @param ui the {@code Ui} object in Duke
     * @param storage the {@code Storage} object in Duke
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            ui.printByeMessage();
            storage.save(taskList.obtainList());
        } catch (IOException e) {
            ui.printBorder();
            ui.printMessage("Please help me find the way to my brain! T_T");
            ui.printBorder();
        }
    }
}
