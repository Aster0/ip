package carl.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import carl.commands.DeadlineCommand;
import carl.exceptions.CarlCommandException;
import carl.exceptions.CarlException;
import carl.util.DateParser;

/**
 * Parses input arguments and creates a new DeadlineCommand object.
 */
public class DeadlineCommandParser implements Parser<DeadlineCommand> {

    private static final String USAGE = "deadline <project_name> /by <date>";

    /**
     * Parses the given input string to extract the task name and deadline date.
     *
     * @param input Raw input string containing the task name and deadline.
     * @return Deadline command initialized with the parsed name and date.
     * @throws CarlException If the input format is incorrect or the date is invalid.
     */
    @Override
    public DeadlineCommand parse(String input) throws CarlException {

        if (!input.contains("/by")) {
            throw new CarlCommandException(USAGE);
        }

        String[] splitBy = input.split("/by", 2);
        String name = splitBy[0].trim();
        String byStr = splitBy[1].trim();

        if (name.isEmpty() || byStr.isEmpty()) {
            throw new CarlCommandException(USAGE);
        }

        try {
            LocalDateTime by = DateParser.dateParser(byStr);
            return new DeadlineCommand(name, by);

        } catch (DateTimeParseException e) {
            throw new CarlCommandException(DateParser.printDateError());
        }

    }
}
