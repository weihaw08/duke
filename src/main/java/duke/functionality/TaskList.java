package duke.functionality;

import duke.exception.EmptyListException;
import duke.exception.IndexNotFoundException;
import duke.tasks.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> list;

    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    public void addTask(Task newTask) {
        this.list.add(newTask);
    }

    public void deleteTask(int index) throws EmptyListException, IndexNotFoundException {
        if (this.list.isEmpty()) {
            throw new EmptyListException();
        } else if (index <= 0 || index > this.list.size()) {
            throw new IndexNotFoundException(index);
        } else {
            this.list.remove(index - 1);
        }
    }

    public Task retrieveTask(int index) throws EmptyListException, IndexNotFoundException {
        if (this.list.isEmpty()) {
            throw new EmptyListException();
        } else if (index <= 0 || index > this.list.size()) {
            throw new IndexNotFoundException(index);
        } else {
            return this.list.get(index - 1);
        }
    }

    public ArrayList<Task> obtainList() {
        return this.list;
    }

    public int size() {
        return this.list.size();
    }
}
