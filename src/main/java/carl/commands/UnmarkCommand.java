package carl.commands;

import carl.exceptions.CarlCommandException;
import carl.exceptions.CarlException;
import carl.task.Task;
import carl.task.TaskList;
import carl.task.TaskManager;
import carl.ui.Ui;

/**
 * Represents a command to mark a task as not done in the task list.
 */
public class UnmarkCommand extends ModifyTaskCommand {

    /**
     * {@inheritDoc}
     * Unmarks the specified task as not done, updates the storage, and informs the user.
     *
     * @param ui      User interface for interacting with the user.
     * @param storage Task manager handling task data persistence.
     * @param tasks   List of current tasks.
     * @param args    Arguments parsed from the user input.
     * @param raw     Raw input string entered by the user.
     * @throws CarlException If the task index is missing, invalid, or the task is already not done.
     */
    @Override
    public void onRun(Ui ui, TaskManager storage, TaskList tasks, String[] args, String raw) throws CarlException {

        if (args.length < 2) {
            throw new CarlCommandException("unmark <number> -"
                    + " you can find the number from saying \"list\"!");
        }

        // unmark x
        int index = Integer.parseInt(args[1]);
        Task task = tasks.markTaskAsUndone(index - 1);
        super.onRun(ui, storage, tasks, args, raw);
        ui.showUnMarkTask(task);
    }
}
