package carl.commands;

import carl.exceptions.CarlException;
import carl.task.TaskList;
import carl.task.TaskManager;
import carl.ui.Ui;

public class SortCommand implements Command {
    @Override
    public CommandResult onRun(Ui ui, TaskManager storage, TaskList tasks, String raw) throws CarlException {
        return CommandResult.success(ui.showTaskList(tasks.getSortedTasks()));
    }
}
