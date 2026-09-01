package carl.commands;

import carl.exceptions.CarlException;
import carl.task.TaskList;
import carl.task.TaskManager;
import carl.ui.Ui;

/**
 * Represents a base command for actions that modify tasks, ensuring changes are saved to storage.
 */
public class ModifyTaskCommand implements Command {
    /**
     * {@inheritDoc}
     * Saves the current task list to storage after a task modification occurs.
     *
     * @param ui      User interface for interacting with the user.
     * @param storage Task manager handling task data persistence.
     * @param tasks   List of current tasks.
     * @param args    Arguments parsed from the user input.
     * @param raw     Raw input string entered by the user.
     * @throws CarlException If an error occurs during execution.
     */
    @Override
    public void onRun(Ui ui, TaskManager storage, TaskList tasks, String[] args, String raw) throws CarlException {
        storage.saveAll(tasks);
    }
}
