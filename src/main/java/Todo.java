public class Todo extends Task {

    public Todo(Item item) {
        super(item);

    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

