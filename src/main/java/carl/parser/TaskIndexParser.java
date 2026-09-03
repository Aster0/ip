package carl.parser;

import carl.commands.Command;
import carl.exceptions.CarlCommandException;
import carl.exceptions.CarlException;

public class TaskIndexParser<T extends Command> implements Parser<T> {


    private CommandIndexFunction<T> commandFunction;

    public TaskIndexParser(CommandIndexFunction<T> commandFunction) {
        this.commandFunction = commandFunction;
    }

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
