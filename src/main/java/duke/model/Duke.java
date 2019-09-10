package duke.model;

import duke.command.Command;
import duke.exception.EmptyDescriptionException;
import duke.exception.WrongInstructionException;
import duke.parser.Parser;
import duke.statistics.WeeklyStatistics;
import duke.storage.Storage;
import duke.ui.Ui;

import java.time.format.DateTimeParseException;

public class Duke {
    private Storage storage;
    private TaskList taskList;
    private WeeklyStatistics stats;
    private Ui ui;

    public Duke() {
        this("./data/tasks.txt", "./data/stats.txt");
    }

    private Duke(String filePath, String statisticsPath) {
        this.storage = new Storage(filePath, statisticsPath);
        this.taskList = new TaskList(storage.loadTasks());
        this.stats = storage.loadStats(taskList);
        this.ui = new Ui();
    }

    public String sayHi() {
        return ui.giveHelloMessage();
    }

    /**
     * Retrieves the relevant response from Duke based on the input command.
     *
     * @param command the input response of the user
     * @return a string representing the response from Duke
     */
    public String getResponse(String command) {
        try {
            Command processed = new Parser(command).parse();
            assert processed != null;
            return processed.execute(taskList, stats, ui, storage);
        } catch (WrongInstructionException e) {
            return e.toString() + "\n" + e.getMessage();
        } catch (EmptyDescriptionException e) {
            return e.toString();
        } catch (NumberFormatException e) {
            return ui.getInvalidIndexMessage();
        } catch (DateTimeParseException e) {
            return ui.getInvalidDateFormatMessage();
        }
    }
}
