import java.util.Scanner;

public class Duke {
    private static final String BORDER = "    ____________________________________________________________";
    private static boolean isBye = false;

    private static void printBorder() {
        System.out.println(BORDER);
    }

    private static void printMessage(String s) {
        printBorder();
        System.out.println("    " + s);
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
            String command = input.next();
            if (command.equals("bye")) {
                isBye = true;
                printMessage("Bye. Hope to see you again soon!");
            } else {
                printMessage(command);
            }
        }

        input.close();
    }
}
