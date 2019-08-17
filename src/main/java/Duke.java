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

    private static void addItem(String item) {
        Task newTask = new Task(item);
        list.add(newTask);
        printMessage("added: " + item);
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
            printMessage("Bye. Hope to see you again soon!");
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
            addItem(command);
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
