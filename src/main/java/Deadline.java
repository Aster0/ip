public class Deadline extends Task {

    protected String by;

    public Deadline(Item item, String by) {
        super(item, TaskType.DEADLINE);
        this.by = by;
    }

    public Deadline(Item item, TaskStatus status, String by) {
        super(item, status, TaskType.DEADLINE);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String toSaveFormat() {
        return String.format("%s | %s", super.toSaveFormat(), by);
    }
}

