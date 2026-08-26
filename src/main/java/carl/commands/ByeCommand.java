package carl.commands;

import carl.task.TaskList;
import carl.task.TaskManager;
import carl.ui.Ui;

public class ByeCommand implements Command {


    @Override
    public void onRun(Ui ui, TaskManager storage, TaskList tasks, String[] args, String raw) {
        ui.showGoodbye();
    }

    @Override
    public boolean isExited() {
        return true; // if carl.commands.ByeCommand is used
    }
}
