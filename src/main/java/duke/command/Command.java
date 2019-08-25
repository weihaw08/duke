package duke.command;

import duke.functionality.TaskList;
import duke.functionality.Ui;
import duke.functionality.Storage;

public abstract class Command {
    String command;

    Command(String command) {
        this.command = command;
    }

    public abstract void execute(TaskList taskList, Ui ui, Storage storage);
}
