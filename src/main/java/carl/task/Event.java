package carl.task;

import java.time.LocalDate;
import java.time.LocalDateTime;

import carl.util.DateParser;

/**
 * Represents an event task with a start date/time and an end date/time.
 */
public class Event extends Task {

    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructs a new Event task with the specified item, start time, and end time.
     * The task status is initialized to NOT_DONE by default.
     *
     * @param item The description or name of the event.
     * @param from The starting date and time of the event.
     * @param to   The ending date and time of the event.
     */
    public Event(Item item, LocalDateTime from, LocalDateTime to) {
        super(item, TaskType.EVENT);
        this.from = from;
        this.to = to;
    }

    /**
     * Constructs an Event task with a specified completion status.
     * This is typically used when loading tasks from a saved file.
     *
     * @param item   The description or name of the event.
     * @param status The completion status of the event.
     * @param from   The starting date and time of the event.
     * @param to     The ending date and time of the event.
     */
    public Event(Item item, TaskStatus status, LocalDateTime from, LocalDateTime to) {
        super(item, status, TaskType.EVENT);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(DateParser.dateFormatter())
                + " to: " + to.format(DateParser.dateFormatter()) + ")";
    }

    @Override
    public String toSaveFormat() {
        return String.format("%s | %s | %s", super.toSaveFormat(), from.format(DateParser.dateFormatterSave()),
                to.format(DateParser.dateFormatterSave()));
    }

    @Override
    protected boolean isOnDate(LocalDate targetDate) {
        return !targetDate.isBefore(this.from.toLocalDate())
                && !targetDate.isAfter(this.to.toLocalDate());
    }
}

