package carl.parser;

import java.time.LocalDate;

import carl.commands.DueCommand;

/**
 * Parses input arguments and creates a new DeadlineCommand object.
 */
public class DueCommandParser implements Parser<DueCommand> {

    /**
     * Parses the given input string to extract the query date.
     *
     * @param input Raw input string containing the target date.
     * @return DueCommand initialized with the specified or current date.
     */
    @Override
    public DueCommand parse(String input) {

        String dateString = LocalDate.now().toString();

        if (!input.isEmpty()) {
            dateString = input;
        }

        return new DueCommand(dateString);

    }
}
