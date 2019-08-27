package duke.functionality;

import duke.tasks.Task;

public class Ui {
    private static final String BORDER = "____________________________________________________________________";
    private static final int USUAL_INDENTATION = 5;
    private boolean isBye;

    public Ui() {
        this.isBye = false;
    }

    public void printBorder() {
        printMessage(BORDER, 4);
    }

    public void printMessage(String msg) {
        printMessage(msg, USUAL_INDENTATION);
    }

    private void printMessage(String msg, int indentation) {
        int rightShift = indentation + msg.length();
        String formatting = "%" + rightShift + "s %n";
        System.out.printf(formatting, msg);
    }

    /**
     * Prints a hello message when Duke is initialised.
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
     * Prints a bye message when the user wishes to exit from Duke.
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
     * Prints out the modification that has been done to a {@code Task} object.
     * @param size the size of the {@code TaskList} object in Duke
     * @param modifiedTask the {@code Task} object that has been modified
     * @param modification the modification done to the {@code Task} object
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

    public boolean isBye() {
        return this.isBye;
    }

}
