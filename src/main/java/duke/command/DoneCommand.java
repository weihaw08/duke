package duke.command;

import duke.functionality.Storage;
import duke.functionality.TaskList;
import duke.functionality.Ui;
import duke.tasks.Task;

/**
 * Represents a command that informs Duke that a particular task has been completed.
 */
public class DoneCommand extends Command {
    private int indexToComplete;

    /**
     * Instantiates a {@code DoneCommand} object.
     *
     * @param indexToComplete the index of the task to be marked as done
     */
    public DoneCommand(int indexToComplete) {
        this.indexToComplete = indexToComplete;
    }

    /**
     * Executes the done command that the {@code DoneCommand} object represents. The {@code Task} object at the index
     * given will be marked as completed, if such an object is present.
     *
     * @param taskList the {@code TaskList} object in Duke
     * @param ui       the {@code Ui} object in Duke
     * @param storage  the {@code Storage} object in Duke
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            Task completedTask = taskList.retrieveTask(indexToComplete);
            completedTask.markAsDone();
            ui.printTaskModification(taskList.size(), completedTask, "done");
        } catch (IndexOutOfBoundsException e) {
            if (taskList.size() == 0) {
                ui.printMessage("O_O Your task list is completely empty!");
            } else {
                ui.printMessage("O_O The index " + indexToComplete + " cannot be found!");
            }
        }
    }
}
