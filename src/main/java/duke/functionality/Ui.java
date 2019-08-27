package duke.functionality;

import duke.tasks.Task;

/**
 * Represents the various user interface features that the user will see while using Duke. This class provides features
 * such as printing messages and beautifying the user interface for Duke.
 */
public class Ui {
    private static final String BORDER = "____________________________________________________________________";
    private static final int USUAL_INDENTATION = 5;
    private boolean isBye;

    /**
     * Instantiates a {@code Ui} object.
     */
    public Ui() {
        this.isBye = false;
    }

    /**
     * Prints a border line that will help to separate consecutive outputs.
     */
    public void printBorder() {
        printMessage(BORDER, 4);
    }

    /**
     * Prints a message that has a fixed left indentation (5 white spaces).
     * @param msg the message to be printed
     */
    public void printMessage(String msg) {
        printMessage(msg, USUAL_INDENTATION);
    }

    private void printMessage(String msg, int indentation) {
        int rightShift = indentation + msg.length();
        String formatting = "%" + rightShift + "s %n";
        System.out.printf(formatting, msg);
    }

    /**
     * Prints the hello message when Duke is initialised.
     */
    public void printHelloMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        printBorder();
        printMessage("Hello! I'm Duke");
        printMessage("What can I do for you?");
        printBorder();
    }

    /**
     * Prints the bye message when the user inputs the "bye" command.
     */
    public void printByeMessage() {
        printBorder();
        this.isBye = true;
        printMessage("Bye. Hope to see you again soon!");
        printBorder();
    }

    private void printListSize(int size) {
        if (size == 1) {
            printMessage("Now you have 1 task in the list.");
        } else {
            printMessage("Now you have " + size + " tasks in the list.");
        }
    }

    /**
     * Notifies the user about the changes that have been made to a task or a task list.
     * @param size the size of the {@code TaskList}
     * @param modifiedTask the {@code Task} object that has been modified
     * @param modification the type of modification: "done", "add", "delete"
     */
    public void printTaskModification(int size, Task modifiedTask, String modification) {
        String messageToPrint;
        boolean isListModified;
        if (modification.equals("add")) {
            messageToPrint = "UWU. A task has been added:";
            isListModified = true;
        } else if (modification.equals("delete")) {
            messageToPrint = "OWO. A task has been removed:";
            isListModified = true;
        } else {
            messageToPrint = "^W^. A task has been marked done:";
            isListModified = false;
        }
        printMessage(messageToPrint);
        printMessage(modifiedTask.toString(), 7);
        if (isListModified) {
            printListSize(size);
        }
    }

    /**
     * Checks if the user has given the "bye" command.
     * @return {@code true} if the user has given the "bye" command and {@code false} if otherwise
     */
    public boolean isBye() {
        return this.isBye;
    }

}
