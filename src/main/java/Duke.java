import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static final String BORDER = "    ____________________________________________________________";
    private static boolean isBye = false;
    private static ArrayList<Task> list = new ArrayList<>();

    private static void printBorder() {
        System.out.println(BORDER);
    }

    private static void printMessage(String msg) {
        System.out.println("     " + msg);
    }

    private static void printListSize() {
        if (list.size() == 1) {
            printMessage("Now you have 1 task in the list.");
        } else {
            printMessage("Now you have " + list.size() + " tasks in the list.");
        }
    }

    // A method to help us check if the instruction entered into Duke is valid.
    private static boolean isCorrectInstruction(String task) {
        return task.equals("todo") || task.equals("deadline") || task.equals("event");
    }

    // This method helps to perform the relevant tasks depending on the input.
    // Exceptions will be thrown if the input is invalid.
    private static void addTask(String task) throws EmptyDescriptionException, WrongInstructionException{
        String[] tokens = task.split(" ");
        Task newTask;
        if (!isCorrectInstruction(tokens[0])) {
            // This will happen if the instruction is invalid.
            throw new WrongInstructionException();
        } else if (tokens.length == 1) {
            // This will happen if the instruction is valid but lacks a description.
            throw new EmptyDescriptionException(tokens[0]);
        } else if (tokens[0].equals("todo")) {
            String todoName = task.replace("todo ", "");
            newTask = new ToDo(todoName);
            list.add(newTask);
        } else if (tokens[0].equals("deadline")) {
            String deadlineName = task.replace("deadline ", "");
            String[] splitDeadline = deadlineName.split(" /by ");
            newTask = new Deadline(splitDeadline[0], splitDeadline[1]);
            list.add(newTask);
        } else {
            String eventName = task.replace("event ", "");
            String[] splitEvent = eventName.split(" /at ");
            newTask = new Event(splitEvent[0], splitEvent[1]);
            list.add(newTask);
        }
        printMessage("Got it. I've added this task:");
        System.out.println("       " + newTask.toString());
        printListSize();
    }

    private static void doneTask(String task) throws EmptyListException, IndexNotFoundException {
        String[] tokens = task.split(" ");
        int indexToRemove = Integer.valueOf(tokens[1]);
        if (list.size() == 0) {
            throw new EmptyListException();
        } else if (indexToRemove > list.size() || indexToRemove <= 0) {
            throw new IndexNotFoundException(indexToRemove);
        } else {
            int taskNumber = Integer.valueOf(tokens[1]) - 1;
            Task completedTask = list.get(taskNumber);
            completedTask.markAsDone();
            printMessage("Nice! I've marked this task as done:");
            System.out.println("     " + completedTask.toString());
        }
    }

    // The following function helps to delete an item inside Duke.
    // An exception will be thrown if the list is empty or when the index is out of bounds.
    private static void deleteTask(String task) throws EmptyListException, IndexNotFoundException {
        String[] tokens = task.split(" ");
        int indexToRemove = Integer.valueOf(tokens[1]);
        if (list.size() == 0) {
            throw new EmptyListException();
        } else if (indexToRemove > list.size() || indexToRemove <= 0) {
            throw new IndexNotFoundException(indexToRemove);
        } else {
            Task toBeRemoved = list.get(indexToRemove - 1);
            list.remove(indexToRemove - 1);
            printMessage("Noted. I've removed this task:");
            System.out.println("       " + toBeRemoved);
            printListSize();
        }
    }

    private static void printList() {
        printMessage("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println("     " + (i + 1) + ". " + list.get(i));
        }
    }

    private static void performCommand(String command) {
        String[] tokens = command.split(" ");
        try {
            switch (tokens[0]) {
            case "bye":
                isBye = true;
                printMessage("Bye. Hope to see you again soon!");
                break;
            case "list":
                printList();
                break;
            case "done":
                doneTask(command);
                break;
            case "delete":
                deleteTask(command);
                break;
            default:
                addTask(command);
                break;
            }
        } catch (EmptyDescriptionException e1) {
            printMessage(e1.toString());
        } catch (WrongInstructionException e2) {
            printMessage(e2.toString());
        } catch (EmptyListException e3) {
            printMessage(e3.toString());
        } catch (IndexNotFoundException e4) {
            printMessage(e4.toString());
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

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

        while(!isBye) {
            String command = input.nextLine();
            printBorder();
            performCommand(command);
            printBorder();
        }

        input.close();
    }
}
