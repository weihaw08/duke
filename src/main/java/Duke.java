import duke.command.Command;
import duke.exception.EmptyDescriptionException;
import duke.exception.InvalidTimeAndDateException;
import duke.exception.WrongInstructionException;
import duke.functionality.Parser;
import duke.functionality.Storage;
import duke.functionality.TaskList;
import duke.functionality.Ui;

import java.text.ParseException;
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
                ui.printBorder();
                command.execute(taskList, ui, storage);
            } catch (WrongInstructionException | InvalidTimeAndDateException e) {
                ui.printBorder();
                ui.printMessage(e.toString());
                ui.printMessage(e.getMessage());
            } catch (EmptyDescriptionException e) {
                ui.printBorder();
                ui.printMessage(e.toString());
            } catch (ParseException e) {
                ui.printBorder();
                ui.printMessage(" Please enter your date in \"dd/mm/yyyy hhmm\" format!");
            } catch (NumberFormatException e) {
                ui.printBorder();
                ui.printMessage("Your task index is not a number!");
                ui.printMessage("Make sure your task index is an integer!");
            } finally {
                ui.printBorder();
            }
        }
        input.close();
    }

    public static void main(String[] args) {
        new Duke("./data/tasks.txt").run();
    }
}
