package duke.parser;

import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.StatsCommand;
import duke.exception.EmptyDescriptionException;
import duke.exception.WrongInstructionException;
import duke.tasks.FormattedDateTime;

import static duke.command.AddCommand.createAddCommand;


/**
 * Represents a command parser that interprets a command. The {@code Parser} object creates a new {@code Command}
 * object if the command that it is fed with is a valid command.
 */
public class Parser {
    private String command;

    /**
     * Instantiates a {@code Parser} object.
     *
     * @param command an input command
     */
    public Parser(String command) {
        this.command = command;
    }

    /**
     * Interprets the command stored in the {@code Parser} instance and produces an output if the command is valid.
     *
     * @return a {@code Command} object representing the command the {@code Parser} is fed with
     * @throws WrongInstructionException   if the command fed to the {@code Parser} object is invalid
     * @throws EmptyDescriptionException   if the command fed to the {@code Parser} object is correct but incomplete
     * @throws NumberFormatException       if the command fed to the {@code Parser} object has incorrect array index
     */
    public Command parse() throws WrongInstructionException, EmptyDescriptionException, NumberFormatException {
        String[] tokens = this.command.split(" ");
        switch (tokens[0]) {
        case "todo":
            return createToDo(tokens);
        case "deadline":
            return createDeadline(tokens);
        case "event":
            return createEvent(tokens);
        case "bye":
            return createByeCommand(tokens);
        case "delete":
            return createDeleteCommand(tokens);
        case "done":
            return createDoneCommand(tokens);
        case "find":
            return createFindCommand(tokens);
        case "list":
            return createListCommand(tokens);
        case "stats":
            return createStatsCommand();
        default:
            throw new WrongInstructionException("Please stick to the list of commands that I know!");
        }
    }

    private Command createStatsCommand() {
        return new StatsCommand();
    }

    private Command createListCommand(String[] tokens) throws WrongInstructionException {
        if (tokens.length != 1) {
            throw new WrongInstructionException("I will only give you your list of tasks if you say \"list\"!");
        } else {
            return new ListCommand();
        }
    }

    private Command createFindCommand(String[] tokens) throws EmptyDescriptionException {
        if (tokens.length == 1) {
            throw new EmptyDescriptionException("find");
        } else {
            String modifiedCommand = this.command.replace("find ", "");
            return new FindCommand(modifiedCommand.trim());
        }
    }

    private Command createDoneCommand(String[] tokens) throws WrongInstructionException, EmptyDescriptionException {
        if (tokens.length > 2) {
            throw new WrongInstructionException("Make me mark a task as done by entering \"done index\"!");
        } else if (tokens.length == 1) {
            throw new EmptyDescriptionException("done");
        } else {
            int index = Integer.parseInt(tokens[1]);
            return new DoneCommand(index);
        }
    }

    private Command createDeleteCommand(String[] tokens) throws WrongInstructionException,
        EmptyDescriptionException {
        if (tokens.length > 2) {
            throw new WrongInstructionException("Make me delete tasks by entering \"delete index\"!");
        } else if (tokens.length == 1) {
            throw new EmptyDescriptionException("delete");
        } else {
            int index = Integer.parseInt(tokens[1]);
            return new DeleteCommand(index);
        }
    }

    private Command createByeCommand(String[] tokens) throws WrongInstructionException {
        if (tokens.length != 1) {
            throw new WrongInstructionException("Say goodbye to me by entering \"bye\"!");
        } else {
            return new ByeCommand();
        }
    }

    private Command createEvent(String[] tokens) throws EmptyDescriptionException, WrongInstructionException {
        if (tokens.length == 1 || tokens[1].equals("/at")) {
            throw new EmptyDescriptionException("event");
        }

        if (!this.command.contains("/at")) {
            throw new WrongInstructionException("I only recognise \"event task_name /at start_date "
                + "- end_date\"!");
        }

        String modifiedCommand = this.command.replace("event", "");
        String[] split = modifiedCommand.split(" /at ");
        String[] splitDates = split[1].split(" - ");
        if (splitDates.length != 2) {
            throw new WrongInstructionException("Please enter 2 dates for an event!");
        } else {
            FormattedDateTime start = new FormattedDateTime(splitDates[0]);
            FormattedDateTime end = new FormattedDateTime(splitDates[1]);
            return createAddCommand(split[0], start, end);
        }

    }

    private Command createToDo(String[] tokens) throws EmptyDescriptionException {
        if (tokens.length < 2) {
            throw new EmptyDescriptionException("todo");
        } else {
            return createAddCommand(command.replace("todo", ""));
        }
    }

    private Command createDeadline(String[] tokens) throws EmptyDescriptionException, WrongInstructionException {
        if (tokens.length == 1 || tokens[1].equals("/by")) {
            throw new EmptyDescriptionException("deadline");
        } else if (!this.command.contains("/by")) {
            throw new WrongInstructionException("I only recognise \"deadline task_name /by date\"!");
        } else {
            String modifiedCommand = this.command.replace("deadline", "");
            String[] split = modifiedCommand.split(" /by ");
            FormattedDateTime deadlineDate = new FormattedDateTime(split[1]);
            return createAddCommand(split[0], deadlineDate);
        }
    }

}