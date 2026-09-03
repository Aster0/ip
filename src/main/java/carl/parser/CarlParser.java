package carl.parser;

import java.util.HashMap;
import java.util.Map;

import carl.commands.*;
import carl.exceptions.CarlException;
import carl.exceptions.CarlUnknownCommandException;
import carl.task.TaskList;
import carl.task.TaskManager;
import carl.ui.Ui;

/**
 * Parses user input and maps it to the corresponding command for execution.
 */
public class CarlParser {
    private Map<String, Parser<? extends Command>> commands = new HashMap<>();

    /**
     * Initializes the parser with a mapping of string commands to their respective parser objects.
     */
    public CarlParser() {
        commands.put("bye", (args) -> new ByeCommand());
        commands.put("list", (args) -> new ListCommand());
        commands.put("mark", new TaskIndexParser<Command>(MarkCommand::new));
        commands.put("unmark", new TaskIndexParser<Command>(UnmarkCommand::new));
        commands.put("deadline", new DeadlineCommandParser());
        commands.put("event", new EventCommandParser());
        commands.put("todo", new TodoCommandParser());
        commands.put("delete", new TaskIndexParser<Command>(DeleteCommand::new));
        commands.put("due", new DueCommandParser());
        commands.put("find", new FindCommandParser());
    }

    /**
     * Parses the raw user input and executes the mapped command.
     *
     * @param input       The full string input typed by the user.
     * @return The parsed Command object.
     * @throws CarlException If the command is unknown or arguments are invalid.
     */
    public Command parseCommands(String input) throws CarlException {
        String[] args = input.split(" ", 2);
        String commandWord = args[0].toLowerCase();
        String arguments = args.length > 1 ? args[1].trim() : "";

        Parser<?> parseCommand = commands.get(commandWord);

        if (parseCommand == null) {
            throw new CarlUnknownCommandException();
        }

        Command command = parseCommand.parse(arguments);

        return command;
    }
}
