package carl.task;

import java.time.LocalDate;
import java.time.LocalDateTime;

import carl.util.DateParser;

/**
 * Represents a task with a specific deadline.
 */
public class Deadline extends Task {

    protected LocalDateTime by;

    /**
     * Constructs a new Deadline task with the specified item and deadline.
     * The task status is initialized to NOT_DONE by default.
     *
     * @param item The description or name of the task.
     * @param by   The date and time the task is due.
     */
    public Deadline(Item item, LocalDateTime by) {
        super(item, TaskType.DEADLINE);
        this.by = by;
    }

    /**
     * Constructs a Deadline task with a specified completion status.
     * This is typically used when loading tasks from a saved file.
     *
     * @param item   The description or name of the task.
     * @param status The completion status of the task.
     * @param by     The date and time the task is due.
     */
    public Deadline(Item item, TaskStatus status, LocalDateTime by) {
        super(item, status, TaskType.DEADLINE);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateParser.dateFormatter()) + ")";
    }

    @Override
    public String toSaveFormat() {
        return String.format("%s | %s", super.toSaveFormat(), by.format(DateParser.dateFormatterSave()));
    }

    @Override
    protected boolean isOnDate(LocalDate targetDate) {
        return targetDate.isEqual(this.by.toLocalDate());
    }
}

