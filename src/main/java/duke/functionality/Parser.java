package duke.functionality;

import duke.command.AddCommand;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.exception.EmptyDescriptionException;
import duke.exception.WrongInstructionException;

/**
 * Represents a command parser that interprets a command. The {@code Parser} object creates a new {@code Command}
 * object if the command that it is fed with is a valid command.
 */
public class Parser {
    private String[] tokens;
    private String command;

    /**
     * Instantiates a {@code Parser} object.
     * @param command an input command
     */
    public Parser(String command) {
        this.tokens = command.split(" ");
        this.command = command;
    }

    private boolean isAddCommand(String command) {
        return command.equals("todo") || command.equals("deadline") || command.equals("event");
    }

    private boolean isDeleteCommand(String command) {
        return command.equals("delete");
    }

    private boolean isDoneCommand(String command) {
        return command.equals("done");
    }

    private boolean isListCommand(String command) {
        return command.equals("list");
    }

    private boolean isByeCommand(String command) {
        return command.equals("bye");
    }

    private boolean isFindCommand(String command) {
        return command.equals("find");
    }

    private boolean isValidInstruction(String instruction) {
        return isAddCommand(instruction) || isDeleteCommand(instruction) || isDoneCommand(instruction)
                || isListCommand(instruction) || isByeCommand(instruction) || isFindCommand(instruction);
    }

    private AddCommand createAddCommand() throws EmptyDescriptionException {
        if (tokens.length == 1 || tokens[1].equals("/by") || tokens[1].equals("/at")) {
            throw new EmptyDescriptionException(tokens[0]);
        } else {
            return new AddCommand(command);
        }
    }

    private DoneCommand createDoneCommand() throws WrongInstructionException {
        if (tokens.length != 2) {
            throw new WrongInstructionException();
        } else {
            return new DoneCommand(command);
        }
    }

    private DeleteCommand createDeleteCommand() throws WrongInstructionException {
        if (tokens.length != 2) {
            throw new WrongInstructionException();
        } else {
            return new DeleteCommand(command);
        }
    }

    private ListCommand createListCommand() throws WrongInstructionException {
        if (tokens.length != 1) {
            throw new WrongInstructionException();
        } else {
            return new ListCommand(command);
        }
    }

    private ByeCommand createByeCommand() throws WrongInstructionException {
        if (tokens.length != 1) {
            throw new WrongInstructionException();
        } else {
            return new ByeCommand(command);
        }
    }

    private FindCommand createFindCommand() {
        return new FindCommand(command);
    }

    /**
     * Interprets the command stored in the {@code Parser} instance and produces an output if the command is valid.
     * @return a {@code Command} object representing the command the {@code Parser} is fed with.
     * @throws WrongInstructionException if the command fed to the {@code Parser} object is invalid
     * @throws EmptyDescriptionException if the command fed to the {@code Parser} object is correct but incomplete.
     */
    public Command parse() throws WrongInstructionException, EmptyDescriptionException {
        if (!isValidInstruction(tokens[0])) {
            throw new WrongInstructionException();
        } else if (isAddCommand(tokens[0])) {
            return createAddCommand();
        } else if (isDeleteCommand(tokens[0])) {
            return createDeleteCommand();
        } else if (isDoneCommand(tokens[0])) {
            return createDoneCommand();
        } else if (isListCommand(tokens[0])) {
            return createListCommand();
        } else if (isByeCommand(tokens[0])) {
            return createByeCommand();
        } else {
            return createFindCommand();
        }
    }

}
