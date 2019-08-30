package duke.functionality;

import duke.tasks.Task;

import java.util.ArrayList;

/**
 * Represents the list that is used to store the tasks in Duke. This class provides utility functions that allow Duke
 * to retrieve, delete and add tasks.
 */
public class TaskList {
    private ArrayList<Task> list;

    /**
     * Instantiates a {@code TaskList} object.
     *
     * @param list an {@code ArrayList} of {@code Task} objects
     */
    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    /**
     * Adds a new {@code Task} object into the {@code TaskList}.
     *
     * @param newTask a {@code Task} object
     */
    public void addTask(Task newTask) {
        this.list.add(newTask);
    }

    /**
     * Deletes a {@code Task} object that is found at a given index.
     *
     * @param index the index where the task to be deleted is
     */
    public void deleteTask(int index) {
        try {
            this.list.remove(index - 1);
        } catch (IndexOutOfBoundsException e) {
            if (this.list.size() == 0) {
                System.out.println("     O_O Your task list is completely empty!");
            } else {
                System.out.println("     O_O The index " + index + " cannot be found!");
            }
        }
    }

    /**
     * Retrieves a {@code Task} object at a given index.
     *
     * @param index the index where the task to be retrieved is at
     * @return a {@code Task} object
     */
    public Task retrieveTask(int index) {
        return this.list.get(index - 1);
    }

    /**
     * Obtains an {@code ArrayList} of {@code Task} objects that are stored in the {@code TaskList} object.
     *
     * @return an {@code ArrayList} of {@code Task} object
     */
    public ArrayList<Task> obtainList() {
        return this.list;
    }

    /**
     * Gives the number of items that are currently inside the {@code TaskList} object.
     *
     * @return the number of items in the {@code TaskList} object
     */
    public int size() {
        return this.list.size();
    }
}
