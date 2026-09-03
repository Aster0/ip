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
public class UnmarkCommand extends TargetedTaskCommand {

    /**
     * Constructs an {@code AddTaskCommand} with the specified task name.
     *
     * @param index index of the Task
     */
    public UnmarkCommand(int index) {
        super(index);
    }

    /**
     * {@inheritDoc}
     * Unmarks the specified task as not done, updates the storage, and informs the user.
     *
     * @param ui      User interface for interacting with the user.
     * @param storage Task manager handling task data persistence.
     * @param tasks   List of current tasks.
     * @param raw     Raw input string entered by the user.
     * @return
     * @throws CarlException If the task index is missing, invalid, or the task is already not done.
     */
    @Override
    public CommandResult execute(Ui ui, TaskManager storage, TaskList tasks, String raw) throws CarlException {


        // unmark x
        System.out.println("unmarked");
        Task task = tasks.markTaskAsUndone(index - 1);

        return CommandResult.success(ui.showUnMarkTask(task));
    }
}
