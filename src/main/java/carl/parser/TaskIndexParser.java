package carl.parser;

import carl.commands.Command;
import carl.exceptions.CarlCommandException;
import carl.exceptions.CarlException;

/**
 * Parses user input containing a task index and instantiates the corresponding command.
 *
 * @param <T> The specific subtype of {@link Command} produced by this parser.
 */
public class TaskIndexParser<T extends Command> implements Parser<T> {


    private CommandIndexFunction<T> commandFunction;

    /**
     * Constructs a {@code TaskIndexParser} with the specified command factory function.
     *
     * @param commandFunction The factory used to instantiate the command using a parsed index.
     */
    public TaskIndexParser(CommandIndexFunction<T> commandFunction) {
        this.commandFunction = commandFunction;
    }

    /**
     * Parses the user input into an integer index and constructs the command.
     *
     * @param input Raw argument string representing the task index.
     * @return The instantiated command of type {@code T}.
     * @throws CarlException If the input is empty or cannot be parsed into a valid integer.
     */
    @Override
    public T parse(String input) throws CarlException {

        System.out.println("Index");
        if (input.isEmpty()) {
            throw new CarlCommandException("Please input a number!"
                    + " you can find the number from saying \"list\"!");
        }

        System.out.println(input);
        try {
            return commandFunction.create(Integer.parseInt(input));
        } catch (NumberFormatException e) {
            throw new CarlCommandException("Input must be a number (e.g., 1)!");
        }


    }
}
