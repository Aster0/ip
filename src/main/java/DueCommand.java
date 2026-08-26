import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class DueCommand implements Command {

    @Override
    public void onRun(Carl carl, TaskManager storage, TaskList tasks, String[] args, String raw) throws CarlException {
        String dateString = LocalDate.now().toString();

        if (args.length > 1) {
            dateString = args[1];
        }

        try {
            LocalDate date = Parser.dateParserWithoutTime(dateString);

            tasks.listItems(date);


        } catch (DateTimeParseException e) {
            throw new CarlEmptyCommandException(Parser.printDateErrorWithoutTime());
        }
        
    }

}
