public class Event extends Task {

    protected String from, to;

    public Event(Item item, String from, String to) {
        super(item);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}

