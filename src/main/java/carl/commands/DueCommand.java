package carl.commands;

import carl.exceptions.CarlCommandException;
import carl.exceptions.CarlException;
import carl.task.TaskList;
import carl.task.TaskManager;
import carl.ui.Ui;
import carl.util.Parser;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class DueCommand implements Command {

    @Override
    public void onRun(Ui ui, TaskManager storage, TaskList tasks, String[] args, String raw) throws CarlException {
        String dateString = LocalDate.now().toString();

        if (args.length > 1) {
            dateString = args[1];
        }

        try {
            LocalDate date = Parser.dateParserWithoutTime(dateString);

            ui.showTaskListDueOn(tasks, date);


        } catch (DateTimeParseException e) {
            throw new CarlCommandException(Parser.printDateErrorWithoutTime());
        }
        
    }

}
