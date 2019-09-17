package duke.command;

import duke.statistics.WeeklyStatistics;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Represents a command to instruct Duke to find tasks that contain specific keyword(s).
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Instantiates a {@code FindCommand} object.
     *
     * @param keyword the keyword that the user wants to search for
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the find command inside the {@code FindCommand} object. Duke will perform a linear search through the
     * {@code TaskList} object to look for the {@code Task} objects that match the specific keyword(s).
     *
     * @param taskList the {@code TaskList} object in duke.model.Duke
     * @param ui       the {@code Ui} object in duke.model.Duke
     * @param storage  the {@code Storage} object in duke.model.Duke
     * @return a string containing the possible matches that are found in the task list
     */
    public String execute(TaskList taskList, WeeklyStatistics stats, Ui ui, Storage storage) {
        boolean isFound = false;
        int count = 0;
        StringBuilder message = new StringBuilder();

        for (int i = 1; i <= taskList.size(); i++) {
            Task task = taskList.retrieveTask(i);
            assert task != null;
            if (task.getTaskName().contains(keyword)) {
                isFound = true;
                count++;
                message.append(count).append(". ").append(task.toString()).append("\n");
            }
        }

        if (isFound) {
            message = new StringBuilder("It's a match!\n").append(message);
        } else {
            message.append("UwU\" No matches found. Please try again.");
        }
        return message.toString();
    }
}
