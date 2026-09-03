package carl.commands;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import carl.exceptions.CarlCommandException;
import carl.exceptions.CarlException;
import carl.task.TaskList;
import carl.task.TaskManager;
import carl.ui.Ui;
import carl.util.DateParser;
/**
 * Represents a command to find and list tasks due on a specific date.
 */
public class DueCommand implements Command {


    private String dateString;

    public DueCommand(String dateString) {
        this.dateString = dateString;
    }

    /**
     * {@inheritDoc}
     * Lists all tasks due on the specified date, or today's date if no date is provided.
     *
     * @param ui      User interface for interacting with the user.
     * @param storage Task manager handling task data persistence.
     * @param tasks   List of current tasks.
     * @param raw     Raw input string entered by the user.
     * @return
     * @throws CarlException If the provided date format is invalid.
     */
    @Override
    public CommandResult onRun(Ui ui, TaskManager storage, TaskList tasks, String raw) throws CarlException {
        try {
            LocalDate date = DateParser.dateParserWithoutTime(dateString);
            return CommandResult.success(ui.showTaskList(tasks.getTasksDueOn(date)));


        } catch (DateTimeParseException e) {
            throw new CarlCommandException(DateParser.printDateErrorWithoutTime());
        }

    }

}
