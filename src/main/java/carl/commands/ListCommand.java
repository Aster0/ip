package carl.commands;

import carl.task.TaskList;
import carl.task.TaskManager;
import carl.ui.Ui;

public class ListCommand implements Command {


    /**
     * {@inheritDoc}
     * Displays all existing tasks to the user.
     *
     * @param ui User interface for interacting with the user.
     * @param storage Task manager handling task data persistence.
     * @param tasks List of current tasks.
     * @param args Arguments parsed from the user input.
     * @param raw Raw input string entered by the user.
     */
    @Override
    public void onRun(Ui ui, TaskManager storage, TaskList tasks, String[] args, String raw) {
        ui.showTaskList(tasks.getAllTasks());
    }
}
