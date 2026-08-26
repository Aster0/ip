import java.time.LocalDate;
import java.time.LocalDateTime;

public class Event extends Task {

    protected LocalDateTime from, to;

    public Event(Item item, LocalDateTime from, LocalDateTime to) {
        super(item, TaskType.EVENT);
        this.from = from;
        this.to = to;
    }

    public Event(Item item, TaskStatus status, LocalDateTime from, LocalDateTime to) {
        super(item, status, TaskType.EVENT);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(Parser.dateFormatter())
                + " to: " + to.format(Parser.dateFormatter()) + ")";
    }

    @Override
    public String toSaveFormat() {
        return String.format("%s | %s | %s", super.toSaveFormat(), from.format(Parser.dateFormatterSave()),
                to.format(Parser.dateFormatterSave()));
    }

    @Override
    protected boolean isOnDate(LocalDate targetDate) {
        return !targetDate.isBefore(this.from.toLocalDate())
                && !targetDate.isAfter(this.to.toLocalDate());
    }
}

