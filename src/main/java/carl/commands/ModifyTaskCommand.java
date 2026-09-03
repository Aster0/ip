package carl.commands;

import carl.exceptions.CarlException;
import carl.task.TaskList;
import carl.task.TaskManager;
import carl.ui.Ui;

/**
 * Represents a base command for actions that modify tasks, ensuring changes are saved to storage.
 */
public abstract class ModifyTaskCommand implements Command {



    /**
     * {@inheritDoc}
     * Saves the current task list to storage after a task modification occurs.
     *
     * @param ui      User interface for interacting with the user.
     * @param storage Task manager handling task data persistence.
     * @param tasks   List of current tasks.
     * @param raw     Raw input string entered by the user.
     * @return
     * @throws CarlException If an error occurs during execution.
     */
    @Override
    public CommandResult onRun(Ui ui, TaskManager storage, TaskList tasks, String raw)
            throws CarlException {
        CommandResult commandResult = execute(ui, storage, tasks, raw);
        storage.saveAll(tasks);

        return commandResult;
    }

    public abstract CommandResult execute(Ui ui, TaskManager storage,
                                          TaskList tasks, String raw) throws CarlException;
}
