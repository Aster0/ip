package carl.commands;

import carl.exceptions.CarlException;
import carl.task.TaskList;
import carl.task.TaskManager;
import carl.ui.Ui;

public interface Command {


    public void onRun(Ui ui, TaskManager storage, TaskList tasks,
                      String[] args, String raw) throws CarlException;


    default public boolean isExited() {
        return false; // if the progarm must be terminated via carl.commands.ByeCommand
    }
}
