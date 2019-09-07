package duke.functionality;

import duke.tasks.Task;

/**
 * Represents the various user interface features that the user will see while using Duke.
 */
public class Ui {

    /**
     * Gives the welcoming greeting from Duke.
     * @return a string representing the hello message from Duke
     */
    public String giveHelloMessage() {
        return "Hello from Duke!!\nWhat can I do for you?";
    }

    /**
     * Gives the farewell message from Duke.
     * @return a string representing the goodbye message from Duke
     */
    public String giveBye() {
        return "Bye. Hope to see you again soon!";
    }

    private String listSizeToString(int size) {
        if (size == 1) {
            return "Now you have 1 task in the list.";
        } else {
            return "Now you have " + size + " tasks in the list.";
        }
    }

    /**
     * Notifies the user about the changes that have been made to a task or a task list.
     *
     * @param size         the size of the {@code TaskList}
     * @param modifiedTask the {@code Task} object that has been modified
     * @param modification the type of modification: "done", "add", "delete"
     * @return a string representing the information of the modification that has been carried out
     */
    public String printTaskModification(int size, Task modifiedTask, String modification) {
        String message;
        boolean isListModified;
        if (modification.equals("add")) {
            message = "UWU. A task has been added:";
            isListModified = true;
        } else if (modification.equals("delete")) {
            message = "OWO. A task has been removed:";
            isListModified = true;
        } else {
            message = "^W^. A task has been marked done:";
            isListModified = false;
        }
        message += "\n  " + modifiedTask.toString() + "\n";
        if (isListModified) {
            message += listSizeToString(size);
        }
        assert !message.equals("");
        return message;
    }


    public String getIndexNotFoundMessage(int index) {
        return "O_O The index " + index + " cannot be found!";
    }


    public String getEmptyListMessage() {
        return "O_O Your task list is completely empty!";
    }


    public String getInvalidDateFormatMessage() {
        return "Please enter your date in \"dd/mm/yyyy hhmm\" format!";
    }


    public String getInvalidIndexMessage() {
        return "Your task index is not a number!\n Make sure your task index is an integer!";
    }

}
