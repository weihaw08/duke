package duke.functionality;

import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.exception.EmptyDescriptionException;
import duke.exception.InvalidTimeAndDateException;
import duke.exception.WrongInstructionException;
import duke.tasks.FormattedDate;

import java.text.ParseException;

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
     * @throws ParseException              if the command fed to the {@code Parser} object has invalid date format
     * @throws InvalidTimeAndDateException if the command fed to the {@code Parser} object has incorrect time and date
     * @throws NumberFormatException       if the command fed to the {@code Parser} object has incorrect array index
     */
    public Command parse() throws WrongInstructionException, EmptyDescriptionException, ParseException,
            InvalidTimeAndDateException, NumberFormatException {
        String[] tokens = this.command.split(" ");
        switch (tokens[0]) {
        case "todo":
            if (tokens.length < 2) {
                throw new EmptyDescriptionException("todo");
            } else {
                return createAddCommand(command.replace("todo", ""));
            }
        case "deadline":
            if (tokens.length == 1 || tokens[1].equals("/by")) {
                throw new EmptyDescriptionException("deadline");
            } else if (!this.command.contains("/by")) {
                throw new WrongInstructionException("I only recognise \"deadline task_name /by date\"!");
            } else {
                String modifiedCommand = this.command.replace("deadline", "");
                String[] split = modifiedCommand.split(" /by ");
                FormattedDate deadlineDate = new FormattedDate(split[1]);
                return createAddCommand(split[0], deadlineDate);
            }
        case "event":
            if (tokens.length == 1 || tokens[1].equals("/at")) {
                throw new EmptyDescriptionException("event");
            } else if (!this.command.contains("/at")) {
                throw new WrongInstructionException("I only recognise \"event task_name /at start_date "
                        + "- end_date\"!");
            } else {
                String modifiedCommand = this.command.replace("event", "");
                String[] split = modifiedCommand.split(" /at ");
                String[] splitDates = split[1].split(" - ");
                if (splitDates.length != 2) {
                    throw new WrongInstructionException("I only recognise \"event event_name /at start_date "
                            + "- end_date\"!");
                } else {
                    FormattedDate start = new FormattedDate(splitDates[0]);
                    FormattedDate end = new FormattedDate(splitDates[1]);
                    if (start.compareTo(end) > 0) {
                        throw new InvalidTimeAndDateException(split[1]);
                    }
                    return createAddCommand(split[0], start, end);
                }
            }
        case "bye":
            if (tokens.length != 1) {
                throw new WrongInstructionException("Say goodbye to me by entering \"bye\"!");
            } else {
                return new ByeCommand();
            }
        case "delete":
            if (tokens.length > 2) {
                throw new WrongInstructionException("Make me delete tasks by entering \"delete index\"!");
            } else if (tokens.length == 1) {
                throw new EmptyDescriptionException("delete");
            } else {
                int index = Integer.parseInt(tokens[1]);
                return new DeleteCommand(index);
            }
        case "done":
            if (tokens.length > 2) {
                throw new WrongInstructionException("Make me mark a task as done by entering \"done index\"!");
            } else if (tokens.length == 1) {
                throw new EmptyDescriptionException("done");
            } else {
                int index = Integer.parseInt(tokens[1]);
                return new DoneCommand(index);
            }
        case "find":
            if (tokens.length == 1) {
                throw new EmptyDescriptionException("find");
            } else {
                String modifiedCommand = this.command.replace("find ", "");
                return new FindCommand(modifiedCommand);
            }
        case "list":
            if (tokens.length != 1) {
                throw new WrongInstructionException("I will only give you your list of tasks if you say \"list\"!");
            } else {
                return new ListCommand();
            }
        default:
            throw new WrongInstructionException("Please stick to the list of commands that I know!");
        }
    }
}