package carl.commands;

import carl.exceptions.CarlException;
import carl.task.TaskList;
import carl.task.TaskManager;
import carl.ui.Ui;

public class FindCommand implements Command {

    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }
    @Override
    public void onRun(Ui ui, TaskManager storage, TaskList tasks, String[] args, String raw) throws CarlException {
        ui.showTaskList(tasks.findTask(keyword));
    }
}
