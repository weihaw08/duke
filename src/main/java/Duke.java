import duke.command.Command;
import duke.exception.EmptyDescriptionException;
import duke.exception.WrongInstructionException;
import duke.functionality.Parser;
import duke.functionality.Storage;
import duke.functionality.TaskList;
import duke.functionality.Ui;
import java.util.Scanner;

public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    private Duke(String filePath) {
        this.storage = new Storage(filePath);
        this.taskList = new TaskList(storage.load());
        this.ui = new Ui();
    }

    private void run() {
        ui.printHelloMessage();
        Scanner input = new Scanner(System.in);
        while (!ui.isBye()) {
            try {
                Command command = new Parser(input.nextLine()).parse();
                command.execute(taskList, ui, storage);
            } catch (WrongInstructionException e) {
                ui.printBorder();
                ui.printMessage(e.toString());
                ui.printBorder();
            } catch (EmptyDescriptionException e) {
                ui.printBorder();
                ui.printMessage(e.toString());
                ui.printBorder();
            }
        }
        input.close();
    }

    public static void main(String[] args) {
        new Duke("./data/tasks.txt").run();
    }
}
