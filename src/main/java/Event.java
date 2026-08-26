public class Event extends Task {

    protected String from, to;

    public Event(Item item, String from, String to) {
        super(item, TaskType.EVENT);
        this.from = from;
        this.to = to;
    }

    public Event(Item item, TaskStatus status, String from, String to) {
        super(item, status, TaskType.EVENT);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    @Override
    public String toSaveFormat() {
        return String.format("%s | %s | %s", super.toSaveFormat(), from, to);
    }
}

