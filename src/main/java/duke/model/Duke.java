package duke.model;

import duke.command.Command;
import duke.exception.EmptyDescriptionException;
import duke.exception.InvalidTimeAndDateException;
import duke.exception.WrongInstructionException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.ui.Ui;

import java.text.ParseException;

public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Duke() {
        this("./data/tasks.txt");
    }

    private Duke(String filePath) {
        this.storage = new Storage(filePath);
        this.taskList = new TaskList(storage.load());
        this.ui = new Ui();
    }

    public String sayHi() {
        return ui.giveHelloMessage();
    }

    /**
     * Retrieves the relevant response from Duke based on the input command.
     * @param command the input response of the user
     * @return a string representing the response from duke.model.Duke
     */
    public String getResponse(String command) {
        try {
            Command processed = new Parser(command).parse();
            assert processed != null;
            return processed.execute(taskList, ui, storage);
        } catch (WrongInstructionException | InvalidTimeAndDateException e) {
            return e.toString() + "\n" + e.getMessage();
        } catch (EmptyDescriptionException e) {
            return e.toString();
        } catch (ParseException e) {
            return ui.getInvalidDateFormatMessage();
        } catch (NumberFormatException e) {
            return ui.getInvalidIndexMessage();
        }
    }
}
