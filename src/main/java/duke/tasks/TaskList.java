package duke.tasks;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Represents the list that is used to store the tasks in Duke. This class provides functions that
 * help Duke to retrieve, delete and add tasks.
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
        assert newTask != null;
        this.list.add(newTask);
    }

    /**
     * Deletes a {@code Task} object that is found at a given index.
     *
     * @param index the index where the task to be deleted is
     */
    public void deleteTask(int index) {
        this.list.remove(index - 1);
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
     * Gives the number of items that are currently inside the {@code TaskList} object.
     *
     * @return the number of items in the {@code TaskList} object
     */
    public int size() {
        return this.list.size();
    }

    /**
     * Counts the total number of completed tasks in the task list.
     * @return the total number of completed tasks in the task list
     */
    public int getNumOfCompletedTasks() {
        int count = 0;
        for (Task task : list) {
            if (task.isDone()) {
                count++;
            }
        }
        return count;
    }

    /**
     * Counts the number of deadline that the user has missed.
     * @return the number of deadline tasks the user has missed
     */
    public long getNumOfMissedDeadlines() {
        LocalDateTime currentTime = LocalDateTime.now();
        return list.stream()
                   .filter(x -> x instanceof Deadline)
                   .filter(x -> !x.isDone())
                   .map(x -> ((Deadline) x).getDateTime())
                   .filter(x -> x.isBefore(currentTime))
                   .count();
    }
}
