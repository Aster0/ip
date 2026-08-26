package carl.commands;

import carl.exceptions.CarlCommandException;
import carl.exceptions.CarlException;
import carl.task.Event;
import carl.task.Item;
import carl.task.TaskList;
import carl.task.TaskManager;
import carl.ui.Ui;
import carl.util.Parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

public class EventCommand extends ModifyTaskCommand {

    private static final String USAGE = "event <project_name> /from <date> /to <date>";

    @Override
    public void onRun(Ui ui, TaskManager storage, TaskList tasks, String[] args, String raw) throws CarlException {
        if (args.length < 2 || !raw.contains("/from") || !raw.contains("/to")) {
            throw new CarlCommandException(USAGE);
        }

        int fromIndex = getIndexOf(args, "/from");
        int toIndex = getIndexOf(args, "/to");

        if (fromIndex > toIndex) {
            throw new CarlCommandException(USAGE);
        }

        String name = String.join(" ", Arrays.copyOfRange(args, 1, fromIndex)).trim();
        String from = String.join(" ", Arrays.copyOfRange(args, fromIndex + 1, toIndex)).trim();
        String to = String.join(" ", Arrays.copyOfRange(args, toIndex + 1, args.length)).trim();

        validateNonEmpty(name, from, to);

        try {
            LocalDateTime timeFrom = Parser.dateParser(from);
            LocalDateTime timeTo = Parser.dateParser(to);

            tasks.addTaskToList(new Event(new Item(name), timeFrom, timeTo));
            super.onRun(ui, storage, tasks, args, raw);

        } catch (DateTimeParseException e) {
            Parser.printDateError();
        }


    }

    private int getIndexOf(String[] args, String word) {
        int byIndex = -1;

        for (int i = 0; i < args.length; i++) {
            String str = args[i];

            if (str.equals(word)) {
                byIndex = i;
            }
        }
        if (byIndex == -1) {
            byIndex = args.length;
        }
        return byIndex;
    }

    private void validateNonEmpty(String name, String from, String to) throws CarlException {
        if (name.isEmpty() || from.isEmpty() || to.isEmpty()) {
            throw new CarlCommandException(USAGE);
        }
    }
}
