package duke.command;

import duke.functionality.Storage;
import duke.functionality.TaskList;
import duke.functionality.Ui;


public abstract class Command {

    public abstract String execute(TaskList taskList, Ui ui, Storage storage);

}
