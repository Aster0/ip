package carl.commands;

import carl.exceptions.CarlException;
import carl.task.Item;
import carl.task.Task;
import carl.task.TaskList;
import carl.task.TaskManager;
import carl.task.Todo;
import carl.ui.Ui;

/**
 * Represents a command to create and add a Todo task.
 */
public class TodoCommand extends AddTaskCommand {

    /**
     * Constructs a {@code TodoCommand} with the specified task name.
     *
     * @param name Name or description of the todo task.
     */
    public TodoCommand(String name) {
        super(name);
    }


    /**
     * {@inheritDoc}
     * Adds the todo task to the task list, saves the list, and informs the user.
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
        Task task = new Todo(new Item(name));
        tasks.addTaskToList(task);
        super.onRun(ui, storage, tasks, args, raw);
        ui.showAddTask(task, tasks.getTasksLeft());
    }
}
