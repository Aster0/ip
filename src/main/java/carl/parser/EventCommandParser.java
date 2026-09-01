package carl.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import carl.commands.EventCommand;
import carl.exceptions.CarlCommandException;
import carl.exceptions.CarlException;
import carl.util.DateParser;

/**
 * Parses input arguments and creates a new EventCommand object.
 */
public class EventCommandParser implements Parser<EventCommand> {
    private static final String USAGE = "event <project_name> /from <date> /to <date>";

    /**
     * Parses the given input string to extract the task name, start date, and end date.
     *
     * @param input Raw input string containing the event details.
     * @return Event command initialized with the parsed name, start date, and end date.
     * @throws CarlException If the input format is invalid, missing fields, or if start date is after end date.
     */
    @Override
    public EventCommand parse(String input) throws CarlException {

        String[] parts = extractParts(input);
        String name = parts[0];
        String fromStr = parts[1];
        String toStr = parts[2];

        if (name.isEmpty() || fromStr.isEmpty() || toStr.isEmpty()) {
            throw new CarlCommandException(USAGE);
        }

        try {
            LocalDateTime from = DateParser.dateParser(fromStr);
            LocalDateTime to = DateParser.dateParser(toStr);

            if (from.isAfter(to)) {
                throw new CarlCommandException("The event start date cannot be after the end date.");
            }

            return new EventCommand(name, from, to);

        } catch (DateTimeParseException e) {
            throw new CarlCommandException(DateParser.printDateError());
        }
    }

    private String[] extractParts(String input) throws CarlException {
        if (!input.contains("/from") || !input.contains("/to")) {
            throw new CarlCommandException(USAGE);
        }

        String[] splitFrom = input.split("/from", 2);
        String[] splitTo = splitFrom[1].split("/to", 2);

        if (splitTo.length < 2) {
            throw new CarlCommandException(USAGE);
        }

        return new String[] {
                splitFrom[0].trim(),
                splitTo[0].trim(),
                splitTo[1].trim()
        };

    }


}
