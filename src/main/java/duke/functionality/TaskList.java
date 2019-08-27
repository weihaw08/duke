package duke.functionality;

import duke.exception.EmptyListException;
import duke.exception.IndexNotFoundException;
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
     * @param list an {@code ArrayList} of {@code Task} objects
     */
    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    /**
     * Adds a new {@code Task} object into the {@code TaskList}.
     * @param newTask a {@code Task} object
     */
    public void addTask(Task newTask) {
        this.list.add(newTask);
    }

    /**
     * Deletes a {@code Task} object that is found at a given index.
     * @param index the index where the task to be deleted is
     * @throws EmptyListException if there are no tasks in the list
     * @throws IndexNotFoundException if there are no tasks in that given index
     */
    public void deleteTask(int index) throws EmptyListException, IndexNotFoundException {
        if (this.list.isEmpty()) {
            throw new EmptyListException();
        } else if (index <= 0 || index > this.list.size()) {
            throw new IndexNotFoundException(index);
        } else {
            this.list.remove(index - 1);
        }
    }

    /**
     * Retrieves a {@code Task} object at a given index.
     * @param index the index where the task to be retrieved is at
     * @return a {@code Task} object
     * @throws EmptyListException if the list is empty
     * @throws IndexNotFoundException if there are no tasks in the given index
     */
    public Task retrieveTask(int index) throws EmptyListException, IndexNotFoundException {
        if (this.list.isEmpty()) {
            throw new EmptyListException();
        } else if (index <= 0 || index > this.list.size()) {
            throw new IndexNotFoundException(index);
        } else {
            return this.list.get(index - 1);
        }
    }

    /**
     * Obtains an {@code ArrayList} of {@code Task} objects that are stored in the {@code TaskList} object.
     * @return an {@code ArrayList} of {@code Task} object
     */
    public ArrayList<Task> obtainList() {
        return this.list;
    }

    /**
     * Gives the number of items that are currently inside the {@code TaskList} object.
     * @return the number of items in the {@code TaskList} object
     */
    public int size() {
        return this.list.size();
    }
}
