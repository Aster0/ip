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
        return "[D]" + super.toString() + " (by: " + by.format(Parser.dateFormatter()) + ")";
    }

    @Override
    public String toSaveFormat() {
        return String.format("%s | %s", super.toSaveFormat(), by.format(Parser.dateFormatterSave()));
    }
}

