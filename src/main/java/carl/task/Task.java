package carl.task;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Task {
    protected Item item;
    protected TaskStatus status;
    protected TaskType type;

    public Task(Item item, TaskType type) {
        this.item = item;
        this.status = TaskStatus.NOT_DONE;
        this.type = type;
    }

    public Task(Item item, TaskStatus status, TaskType type) {
        this.item = item;
        this.status = status;
        this.type = type;
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

    public String toSaveFormat() {
        return String.format("%s | %d | %s", type, status.toInt(), item);

    }

    public static Task of(TaskData data) {
        return switch (data.type) {
            case TODO -> new Todo(data.item, data.status);
            case EVENT -> new Event(data.item, data.status, data.from, data.to);
            case DEADLINE -> new Deadline(data.item, data.status, data.from);
        };
    }


    public boolean hasNameMatch(String keyword) {
        return item.hasNameMatch(keyword);
    }

    public boolean isDueOn(LocalDate targetDate) {
        return isOnDate(targetDate);
    }

    protected boolean isOnDate(LocalDate targetDate) {
        return false;
    }


    public static class TaskData {
        private TaskType type;
        private Item item;
        private TaskStatus status;
        private LocalDateTime from, to;



        public TaskData(TaskType type, TaskStatus status, Item item, LocalDateTime from, LocalDateTime to) {
            this.type = type;
            this.item = item;
            this.status = status;
            this.from = from;
            this.to = to;
        }
    }
}
