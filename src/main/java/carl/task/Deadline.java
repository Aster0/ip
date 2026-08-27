package carl.task;

import carl.util.DateParser;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Deadline extends Task {

    protected LocalDateTime by;

    public Deadline(Item item, LocalDateTime by) {
        super(item, TaskType.DEADLINE);
        this.by = by;
    }

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

