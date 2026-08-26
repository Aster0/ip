package carl.commands;

import carl.exceptions.CarlCommandException;
import carl.exceptions.CarlException;
import carl.task.*;
import carl.ui.Ui;
import carl.util.Parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

public class DeadlineCommand extends ModifyTaskCommand {


    @Override
    public void onRun(Ui ui, TaskManager storage, TaskList tasks, String[] args, String raw) throws CarlException {

        if (args.length < 2 || !raw.contains("/by")) {
            throw new CarlCommandException("deadline <project_name> /by <date>");
        }

        int byIndex = getByIndex(args);
        String name = String.join(" ", Arrays.copyOfRange(args, 1, byIndex)).trim();
        String by = String.join(" ", Arrays.copyOfRange(args, byIndex + 1, args.length)).trim();

        validateNonEmpty(name, by);

        try {
            LocalDateTime timeBy = Parser.dateParser(by);

            Task task = new Deadline(new Item(name), timeBy);
            tasks.addTaskToList(task);
            super.onRun(ui, storage, tasks, args, raw);

            ui.showAddTask(task, tasks.getTasksLeft());
        } catch (DateTimeParseException e) {
            throw new CarlCommandException(Parser.printDateError());
        }

    }

    private void validateNonEmpty(String name, String by) throws CarlException {
        if (by.isEmpty() || name.isEmpty()) {
            throw new CarlCommandException("deadline <project_name> /by <date>");
        }

    }

    private int getByIndex(String[] args) {
        int byIndex = -1;

        for (int i = 0; i < args.length; i++) {
            String str = args[i];

            if (str.equals("/by")) {
                byIndex = i;
            }
        }
        if (byIndex == -1) {
            byIndex = args.length;
        }
        return byIndex;
    }
}
