public class Task {
    protected Item item;
    protected boolean isDone;

    public Task(Item item) {
        this.item = item;
        this.isDone = false;
    }

    private String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), this.item);
    }


    public void markAsDone() {
        this.isDone = true;
    }

    public void unMarkAsDone() {
        this.isDone = false;
    }
}
