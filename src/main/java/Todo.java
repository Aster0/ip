public class Todo extends Task {

    public Todo(Item item) {
        super(item, TaskType.TODO);

    }

    public Todo(Item item, TaskStatus status) {
        super(item, status, TaskType.TODO);

    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

