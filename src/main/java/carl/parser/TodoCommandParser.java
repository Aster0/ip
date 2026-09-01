package carl.parser;

import carl.commands.TodoCommand;
import carl.exceptions.CarlCommandException;
import carl.exceptions.CarlException;

/**
 * Parses input arguments and creates a new TodoCommand object.
 */
public class TodoCommandParser implements Parser<TodoCommand> {

    /**
     * Parses the given input string to extract the todo task description.
     *
     * @param input Raw input string containing the todo description.
     * @return Todo command initialized with the specified description.
     * @throws CarlException If the description is empty.
     */
    @Override
    public TodoCommand parse(String input) throws CarlException {

        if (input.isEmpty()) {
            throw new CarlCommandException("todo <project_name>");
        }

        return new TodoCommand(input);
    }
}
