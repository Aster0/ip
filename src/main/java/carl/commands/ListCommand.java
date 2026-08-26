package carl.commands;

import carl.task.TaskList;
import carl.task.TaskManager;
import carl.ui.Ui;

public class ListCommand implements Command {


    @Override
    public void onRun(Ui ui, TaskManager storage, TaskList tasks, String[] args, String raw) {
        ui.showTaskList(tasks.getAllTasks());
    }
}
