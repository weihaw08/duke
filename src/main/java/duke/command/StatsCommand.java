package duke.command;

import duke.model.TaskList;
import duke.statistics.WeeklyStatistics;
import duke.storage.Storage;
import duke.ui.Ui;

public class StatsCommand extends Command {

    public String execute(TaskList taskList, WeeklyStatistics stats, Ui ui, Storage storage) {
        return stats.computeAllStatistics();
    }
}
