package duke.command;

import duke.model.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;


public abstract class Command {

    public abstract String execute(TaskList taskList, Ui ui, Storage storage);

}
