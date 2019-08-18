import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static final String BORDER = "    ____________________________________________________________";
    private static boolean isBye = false;
    private static ArrayList<Task> list = new ArrayList<>();

    private static void printBorder() {
        System.out.println(BORDER);
    }

    private static void addTask(String task) {
        String[] tokens = task.split(" ");
        Task newTask;
        if (tokens[0].equals("todo")) {
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
        System.out.println("     Got it. I've added this task:");
        System.out.println("       " + newTask.toString());
        if (list.size() == 1) {
            System.out.println("     Now you have 1 task in the list.");
        } else {
            System.out.println("     Now you have " + list.size() + " tasks in the list.");
        }
    }

    private static void printList() {
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println("     " + (i + 1) + ". " + list.get(i));
        }
    }

    private static void performCommand(String command) {
        String[] tokens = command.split(" ");
        switch (tokens[0]) {
        case "bye":
            isBye = true;
            System.out.println("     Bye. Hope to see you again soon!");
            break;
        case "list":
            printList();
            break;
        case "done":
            int taskNumber = Integer.valueOf(tokens[1]) - 1;
            Task completedTask = list.get(taskNumber);
            completedTask.markAsDone();
            break;
        default:
            addTask(command);
            break;
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
        System.out.println("     Hello! I'm Duke");
        System.out.println("     What can I do for you?");
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
