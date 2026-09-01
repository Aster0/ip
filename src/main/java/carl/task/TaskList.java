package carl.task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import carl.exceptions.CarlCommandException;
import carl.exceptions.CarlException;
import carl.exceptions.CarlUnknownTaskException;

/**
 * Represents a list of tasks and provides operations to manage them.
 */
public class TaskList {

    private List<Task> tasks;
    /**
     * Constructs a TaskList with the given list of tasks.
     *
     * @param tasks The initial list of tasks.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }


    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    public void addTaskToList(Task task) {
        tasks.add(task);
    }

    /**
     * Converts the current list of tasks into a formatted string for saving to storage.
     *
     * @return A string representation of all tasks for file storage.
     */
    public String toSaveString() {
        StringBuilder sb = new StringBuilder();

        for (Task task : tasks) {
            sb.append(task.toSaveFormat())
                    .append(System.lineSeparator());
            // System.lineSeparator = \n but work for all OS
        }

        return sb.toString();
    }
    /**
     * Gets the number of tasks currently in the list.
     *
     * @return The number of tasks.
     */
    public int getTasksLeft() {
        return tasks.size();
    }

    /**
     * Retrieves a copy of all tasks in the list.
     *
     * @return A list containing all tasks.
     */
    public List<Task> getAllTasks() {

        return new ArrayList<>(this.tasks);
    }

    /**
     * Retrieves a list of tasks that are due on the specified date.
     *
     * @param date The date to filter tasks by.
     * @return A list of tasks due on the given date.
     */
    public List<Task> getTasksDueOn(LocalDate date) {

        List<Task> tasks = new ArrayList<>();

        for (Task task : this.tasks) {
            if (task.isDueOn(date)) {
                tasks.add(task);
            }
        }

        return tasks;
    }

    /**
     * Finds tasks that match the given keyword.
     *
     * @param keyword The keyword to search for in task names.
     * @return A list of tasks that match the keyword.
     */
    public List<Task> findTask(String keyword) {

        List<Task> tasks = new ArrayList<>();

        for (Task task : this.tasks) {
            if (task.hasNameMatch(keyword)) {
                tasks.add(task);
            }
        }

        return tasks;
    }

    /**
     * Marks the task at the specified index as done.
     *
     * @param index The list index of the task to mark as done.
     * @return The task that was marked as done.
     * @throws CarlCommandException If the index is invalid or the task is already done.
     */
    public Task markTaskAsDone(int index) throws CarlCommandException {

        if (index < 0 || index >= tasks.size()) {
            throw new CarlCommandException("Invalid task number! Check \"list\"!");
        }

        Task task = tasks.get(index);

        if (!task.markAsDone()) {
            throw new CarlCommandException("Task is already marked as done, cannot be marked again!");
        }


        return task;

    }

    /**
     * Deletes the task at the specified index.
     *
     * @param index The list index of the task to delete.
     * @return The task that was deleted.
     * @throws CarlException If the index is out of bounds or the task doesn't exist.
     */
    public Task deleteTask(int index) throws CarlException {

        if (index >= tasks.size()) {
            throw new CarlUnknownTaskException();
        }

        Task task = tasks.get(index);

        if (task == null) {
            throw new CarlUnknownTaskException();
        }

        tasks.remove(index);

        return task;

    }

    /**
     * Marks the task at the specified index as not done.
     *
     * @param index The list index of the task to unmark.
     * @return The task that was unmarked.
     * @throws CarlCommandException If the index is invalid or the task is already not done.
     */
    public Task markTaskAsUndone(int index) throws CarlCommandException {

        if (index < 0 || index >= tasks.size()) {
            throw new CarlCommandException("Invalid task number! Check \"list\"!");
        }

        Task task = tasks.get(index);

        if (!task.unMarkAsDone()) {

            throw new CarlCommandException("Task is already not done, cannot be unmarked!");
        }

        return task;



    }
}
