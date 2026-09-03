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
public class DeleteCommand extends TargetedTaskCommand {

    /**
     * Constructs an {@code AddTaskCommand} with the specified task name.
     *
     * @param index index of the Task
     */
    public DeleteCommand(int index) {
        super(index);
    }

    /**
     * {@inheritDoc}
     * Deletes the specified task by its 1-based index, saves the updated list, and notifies the user.
     *
     * @param ui      User interface for interacting with the user.
     * @param storage Task manager handling task data persistence.
     * @param tasks   List of current tasks.
     * @param raw     Raw input string entered by the user.
     * @return
     * @throws CarlException If the index argument is missing, invalid, or out of range.
     */
    @Override
    public CommandResult execute(Ui ui, TaskManager storage, TaskList tasks, String raw) throws CarlException {

        // delete x
        Task task = tasks.deleteTask(index - 1);


        return CommandResult.success(ui.showDeleteTask(task, tasks.getTasksLeft()));


    }
}
