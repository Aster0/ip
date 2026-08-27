package carl.commands;

import carl.exceptions.CarlCommandException;
import carl.exceptions.CarlException;
import carl.task.*;
import carl.ui.Ui;
import carl.util.DateParser;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

/**
 * Represents a command to create and add a deadline task.
 */
public class DeadlineCommand extends AddTaskCommand {

    /** Due date and time of the deadline task. */
    private LocalDateTime by;

    /**
     * Constructs a {@code DeadlineCommand} with the specified task name and due date.
     *
     * @param name Name or description of the deadline task.
     * @param by Due date and time of the task.
     */
    public DeadlineCommand(String name, LocalDateTime by) {
        super(name);
        this.by = by;
    }


    /**
     * {@inheritDoc}
     * Adds the specified deadline task to the task list, saves the list, and informs the user.
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

        Task task = new Deadline(new Item(name), by);
        tasks.addTaskToList(task);
        super.onRun(ui, storage, tasks, args, raw);
        ui.showAddTask(task, tasks.getTasksLeft());


    }
}
