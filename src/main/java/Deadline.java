public class Deadline extends Task {

    protected String by;

    public Deadline(Item item, String by) {
        super(item);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}

