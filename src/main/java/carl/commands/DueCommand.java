package carl.commands;

import carl.exceptions.CarlCommandException;
import carl.exceptions.CarlException;
import carl.task.TaskList;
import carl.task.TaskManager;
import carl.ui.Ui;
import carl.util.DateParser;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Represents a command to find and list tasks due on a specific date.
 */
public class DueCommand implements Command {

    /**
     * {@inheritDoc}
     * Lists all tasks due on the specified date, or today's date if no date is provided.
     *
     * @param ui User interface for interacting with the user.
     * @param storage Task manager handling task data persistence.
     * @param tasks List of current tasks.
     * @param args Arguments parsed from the user input.
     * @param raw Raw input string entered by the user.
     * @throws CarlException If the provided date format is invalid.
     */
    @Override
    public void onRun(Ui ui, TaskManager storage, TaskList tasks, String[] args, String raw) throws CarlException {
        String dateString = LocalDate.now().toString();

        if (args.length > 1) {
            dateString = args[1];
        }

        try {
            LocalDate date = DateParser.dateParserWithoutTime(dateString);

            ui.showTaskList(tasks.getTasksDueOn(date));


        } catch (DateTimeParseException e) {
            throw new CarlCommandException(DateParser.printDateErrorWithoutTime());
        }
        
    }

}
