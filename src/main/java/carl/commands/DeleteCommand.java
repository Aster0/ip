package carl.commands;

import carl.exceptions.CarlCommandException;
import carl.exceptions.CarlException;
import carl.task.Task;
import carl.task.TaskList;
import carl.task.TaskManager;
import carl.ui.Ui;

/**
 * Represents a command to delete an existing task from the task list.
 */
public class DeleteCommand extends ModifyTaskCommand {

    /**
     * {@inheritDoc}
     * Deletes the specified task by its 1-based index, saves the updated list, and notifies the user.
     *
     * @param ui User interface for interacting with the user.
     * @param storage Task manager handling task data persistence.
     * @param tasks List of current tasks.
     * @param args Arguments parsed from the user input.
     * @param raw Raw input string entered by the user.
     * @throws CarlException If the index argument is missing, invalid, or out of range.
     */
    @Override
    public void onRun(Ui ui, TaskManager storage, TaskList tasks, String[] args, String raw) throws CarlException {
        if (args.length < 2) {
            throw new CarlCommandException("delete <number> -" +
                    " you can find the number from saying \"list\"!");
        }

        // mark x
        int index = Integer.parseInt(args[1]);
        Task task = tasks.deleteTask(index - 1);
        ui.showDeleteTask(task, tasks.getTasksLeft());

        super.onRun(ui, storage, tasks, args, raw);


    }
}
