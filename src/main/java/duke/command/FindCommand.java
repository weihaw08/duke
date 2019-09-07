package duke.command;

import duke.functionality.Storage;
import duke.functionality.TaskList;
import duke.functionality.Ui;
import duke.tasks.Task;

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
     * @param taskList the {@code TaskList} object in Duke
     * @param ui       the {@code Ui} object in Duke
     * @param storage  the {@code Storage} object in Duke
     * @return a string containing the possible matches that are found in the task list
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        boolean isFound = false;
        int count = 0;
        StringBuilder message = new StringBuilder();
        for (int i = 1; i <= taskList.size(); i++) {
            Task task = taskList.retrieveTask(i);
            assert task != null;
            if (task.getTaskName().contains(keyword)) {
                if (!isFound) {
                    message.append("It's a match!\n");
                    isFound = true;
                }
                count++;
                message.append(count).append(". ").append(task.toString()).append("\n");
            }
        }
        if (!isFound) {
            message = new StringBuilder("UwU\" No matches found. Please try again.");
        }
        return message.toString();
    }
}
