package duke.command;

import java.io.IOException;
import duke.functionality.TaskList;
import duke.functionality.Ui;
import duke.functionality.Storage;


public class ByeCommand extends Command {

    public ByeCommand(String byeCommand) {
        super(byeCommand);
    }

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
