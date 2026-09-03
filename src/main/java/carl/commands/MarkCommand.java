package carl.commands;

import carl.exceptions.CarlException;
import carl.task.Task;
import carl.task.TaskList;
import carl.task.TaskManager;
import carl.ui.Ui;

/**
 * Represents a command to mark a task as completed in the task list.
 */
public class MarkCommand extends TargetedTaskCommand {

    /**
     * Constructs an {@code AddTaskCommand} with the specified task name.
     *
     * @param index index of the Task
     */
    public MarkCommand(int index) {
        super(index);
    }

    /**
     * {@inheritDoc}
     * Marks the specified task as done, updates the storage, and informs the user.
     *
     * @param ui      User interface for interacting with the user.
     * @param storage Task manager handling task data persistence.
     * @param tasks   List of current tasks.
     * @param raw     Raw input string entered by the user.
     * @return
     * @throws CarlException If the task index is missing, invalid, or the task is already marked as done.
     */
    @Override
    public CommandResult execute(Ui ui, TaskManager storage, TaskList tasks, String raw) throws CarlException {

        System.out.println("MARKED!");
        // mark x
        Task task = tasks.markTaskAsDone(index - 1);

        return CommandResult.success(ui.showMarkTaskAsDone(task));

    }
}
