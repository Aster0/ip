package carl.parser;

import carl.commands.DeadlineCommand;
import carl.commands.DueCommand;
import carl.exceptions.CarlCommandException;
import carl.exceptions.CarlException;
import carl.util.DateParser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * Parses input arguments and creates a new DeadlineCommand object.
 */
public class DueCommandParser implements Parser<DueCommand> {

    private static final String USAGE = "deadline <project_name> /by <date>";

    /**
     * Parses the given input string to extract the task name and deadline date.
     *
     * @param input Raw input string containing the task name and deadline.
     * @return Deadline command initialized with the parsed name and date.
     * @throws CarlException If the input format is incorrect or the date is invalid.
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
