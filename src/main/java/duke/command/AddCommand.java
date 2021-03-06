package duke.command;

import duke.statistics.WeeklyStatistics;
import duke.storage.Storage;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.FormattedDateTime;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.ToDo;
import duke.ui.Ui;

/**
 * Represents the command that will add a {@code Task} object into a {@code TaskList} object.
 */
public class AddCommand extends Command {
    private String taskName;
    private FormattedDateTime date1;
    private FormattedDateTime date2;

    private AddCommand(String taskName, FormattedDateTime date1, FormattedDateTime date2) {
        this.taskName = taskName.trim();
        this.date1 = date1;
        this.date2 = date2;
    }

    /**
     * Instantiates an {@code AddCommand} object depending on the number of {@code FormattedDate} objects given
     * by the user.
     *
     * @param taskName a string that indicates the name of the {@code Task} object
     * @param dates    the timing of the dates if applicable
     * @return an {@code AddCommand} object
     */
    public static AddCommand createAddCommand(String taskName, FormattedDateTime... dates) {
        if (dates.length == 0) {
            return new AddCommand(taskName, null, null);
        } else if (dates.length == 1) {
            return new AddCommand(taskName, dates[0], null);
        } else {
            return new AddCommand(taskName, dates[0], dates[1]);
        }
    }

    /**
     * Executes the add command that the {@code AddCommand} object represents. A {@code Task} object will be
     * added to the {@code TaskList} object.
     *
     * @param taskList the {@code TaskList} object in Duke
     * @param ui       the {@code Ui} object in Duke
     * @param storage  the {@code Storage} object in Duke
     * @return a string containing the information of the task that has been added
     */
    public String execute(TaskList taskList, WeeklyStatistics stats, Ui ui, Storage storage) {
        Task newTask;
        if (date1 == null && date2 == null) {
            newTask = new ToDo(taskName);
        } else if (date2 == null) {
            newTask = new Deadline(taskName, date1);
        } else {
            newTask = new Event(taskName, date1, date2);
        }
        stats.addLatestIncompleteTask();
        taskList.addTask(newTask);
        return ui.getTaskModificationNotice(taskList.size(), newTask, "add");
    }
}
