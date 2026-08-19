public class Task {
    protected Item item;
    protected TaskStatus status;

    public Task(Item item) {
        this.item = item;
        this.status = TaskStatus.NOT_DONE;
    }


    @Override
    public String toString() {
        return String.format("%s %s", status, this.item);
    }


    public boolean markAsDone() {
        if (this.status == TaskStatus.DONE) {
            return false;
        }

        this.status = TaskStatus.DONE;
        return true;
    }

    public boolean unMarkAsDone() {
        if (this.status == TaskStatus.NOT_DONE) {
            return false;
        }

        this.status = TaskStatus.NOT_DONE;
        return true;
    }
}
