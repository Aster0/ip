package carl.task;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Represents a generic task in the application.
 */
public class Task {
    protected Item item;
    protected TaskStatus status;
    protected TaskType type;

    /**
     * Constructs a Task with the specified item and type.
     * The task is initialized as not done by default.
     *
     * @param item The description or name of the task.
     * @param type The type of the task (e.g., TODO, DEADLINE).
     */
    public Task(Item item, TaskType type) {
        this.item = item;
        this.status = TaskStatus.NOT_DONE;
        this.type = type;
    }

    /**
     * Constructs a Task with the specified item, status, and type.
     *
     * @param item   The description or name of the task.
     * @param status The current completion status of the task.
     * @param type   The type of the task.
     */
    public Task(Item item, TaskStatus status, TaskType type) {
        this.item = item;
        this.status = status;
        this.type = type;
    }

    @Override
    public String toString() {
        return String.format("%s %s", status, this.item);
    }

    /**
     * Marks the task as done.
     *
     * @return true if the task was successfully marked as done, false if it was already done.
     */
    public boolean markAsDone() {
        System.out.println(this.status + " STATUS");
        if (this.status == TaskStatus.DONE) {
            return false;
        }

        this.status = TaskStatus.DONE;
        return true;
    }

    /**
     * Marks the task as not done.
     *
     * @return true if the task was successfully marked as not done, false if it was already not done.
     */
    public boolean unMarkAsDone() {
        if (this.status == TaskStatus.NOT_DONE) {
            return false;
        }

        this.status = TaskStatus.NOT_DONE;
        return true;
    }

    /**
     * Formats the task into a string representation suitable for saving to storage.
     *
     * @return The formatted string for file saving.
     */
    public String toSaveFormat() {
        return String.format("%s | %d | %s", type, status.toInt(), item);

    }

    /**
     * Factory method to create a specific Task instance based on provided TaskData.
     *
     * @param data The data object containing task details.
     * @return A specific Task object (Todo, Event, or Deadline).
     */
    public static Task of(TaskData data) {
        return switch (data.type) {
            case TODO -> new Todo(data.item, data.status);
            case EVENT -> new Event(data.item, data.status, data.from, data.to);
            case DEADLINE -> new Deadline(data.item, data.status, data.from);
        };
    }

    /**
     * Checks if the task's name matches the given keyword.
     *
     * @param keyword The keyword to search for.
     * @return true if the name contains the keyword, false otherwise.
     */
    public boolean hasNameMatch(String keyword) {
        return item.hasNameMatch(keyword);
    }

    /**
     * Checks if the task is due on the specified target date.
     *
     * @param targetDate The date to check against.
     * @return true if the task is due on the target date, false otherwise.
     */
    public boolean isDueOn(LocalDate targetDate) {
        return isOnDate(targetDate);
    }

    /**
     * Helper method to determine if the task falls on a specific date.
     * Overridden by subclasses that have specific dates.
     *
     * @param targetDate The date to check against.
     * @return true if the task is on the target date, false otherwise.
     */
    protected boolean isOnDate(LocalDate targetDate) {
        return false;
    }


    /**
     * A Data Transfer Object used for parsing and instantiating specific Task types.
     */
    public static class TaskData {
        private TaskType type;
        private Item item;
        private TaskStatus status;
        private LocalDateTime from;
        private LocalDateTime to;

        /**
         * Constructs a TaskData object with all necessary fields for any task type.
         *
         * @param type   The type of the task.
         * @param status The completion status of the task.
         * @param item   The description/item of the task.
         * @param from   The start or deadline time (if applicable).
         * @param to     The end time (if applicable).
         */
        public TaskData(TaskType type, TaskStatus status, Item item, LocalDateTime from, LocalDateTime to) {
            this.type = type;
            this.item = item;
            this.status = status;
            this.from = from;
            this.to = to;
        }
    }
}
