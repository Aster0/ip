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
