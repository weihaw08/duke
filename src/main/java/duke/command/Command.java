package duke.command;

import duke.statistics.WeeklyStatistics;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;


public abstract class Command {

    public abstract String execute(TaskList taskList, WeeklyStatistics stats, Ui ui, Storage storage);

}
