package carl.commands;

import java.time.LocalDateTime;

import carl.exceptions.CarlException;
import carl.task.Event;
import carl.task.Item;
import carl.task.Task;
import carl.task.TaskList;
import carl.task.TaskManager;
import carl.ui.Ui;

/**
 * Represents a command to create and add an event task.
 */
public class EventCommand extends AddTaskCommand {

    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Constructs an {@code EventCommand} with the specified task name, start date, and end date.
     *
     * @param name Name or description of the event task.
     * @param from Start date and time of the event.
     * @param to End date and time of the event.
     */
    public EventCommand(String name, LocalDateTime from, LocalDateTime to) {
        super(name);
        this.from = from;
        this.to = to;
    }

    /**
     * {@inheritDoc}
     * Adds the event task to the task list, saves the list, and informs the user.
     *
     * @param ui User interface for interacting with the user.
     * @param storage Task manager handling task data persistence.
     * @param tasks List of current tasks.
     * @param args Arguments parsed from the user input.
     * @param raw Raw input string entered by the user.
     * @throws CarlException If an error occurs during execution.
     */
    @Override
    public void onRun(Ui ui, TaskManager storage, TaskList tasks, String[] args, String raw) throws CarlException {
        addTask(name, from, to, tasks, ui);
        super.onRun(ui, storage, tasks, args, raw);

    }

    private void addTask(String name, LocalDateTime from, LocalDateTime to, TaskList tasks, Ui ui) {
        Task task = new Event(new Item(name), from, to);
        tasks.addTaskToList(task);
        ui.showAddTask(task, tasks.getTasksLeft());
    }

}
