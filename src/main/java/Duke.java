import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static final String BORDER = "    ____________________________________________________________";
    private static boolean isBye = false;
    private static ArrayList<String> list = new ArrayList<>();

    private static void printBorder() {
        System.out.println(BORDER);
    }

    private static void printMessage(String msg) {
        printBorder();
        System.out.println("    " + msg);
        printBorder();
    }

    private static void addItem(String item) {
        list.add(item);
        printMessage("added: " + item);
    }

    private static void printList() {
        printBorder();
        for (int i = 0; i < list.size(); i++) {
            System.out.println("    " + (i + 1) + ". " + list.get(i));
        }
        printBorder();
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
        System.out.println("    Hello! I'm Duke");
        System.out.println("    What can I do for you?");
        printBorder();

        while(!isBye) {
            String command = input.nextLine();
            if (command.equals("bye")) {
                isBye = true;
                printMessage("Bye. Hope to see you again soon!");
            } else if (command.equals("list")) {
                printList();
            } else {
                addItem(command);
            }
        }

        input.close();
    }
}
