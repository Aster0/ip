package carl.parser;

import carl.commands.*;
import carl.exceptions.CarlException;
import carl.exceptions.CarlUnknownCommandException;
import carl.task.TaskList;
import carl.task.TaskManager;
import carl.ui.Ui;

import java.util.HashMap;
import java.util.Map;

public class CarlParser {
    private Map<String, Parser<? extends Command>> commands = new HashMap<>();
    
    // TODO: Parser for other commands
    public CarlParser() {
        commands.put("bye", (args) -> new ByeCommand());
        commands.put("list", (args) -> new ListCommand());
        commands.put("mark", (args) -> new MarkCommand());
        commands.put("unmark", (args) -> new UnmarkCommand());
        commands.put("deadline", new DeadlineCommandParser());
        commands.put("event", new EventCommandParser());
        commands.put("todo", new TodoCommandParser());
        commands.put("delete", (args) -> new DeleteCommand());
        commands.put("due", (args) -> new DueCommand());
    }

    public Command parseCommands(String input, Ui ui, TaskManager taskManager, TaskList tasks) throws CarlException {
        String[] args = input.split(" ", 2);
        String commandWord = args[0].toLowerCase();
        String arguments = args.length > 1 ? args[1].trim() : "";

        Parser<?> parseCommand = commands.get(commandWord);

        if (parseCommand == null) {
            throw new CarlUnknownCommandException();
        }

        Command command = parseCommand.parse(arguments);
        command.onRun(ui, taskManager, tasks, args, input);

        return command;
    }
}
