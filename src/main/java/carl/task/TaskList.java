package carl.task;

import carl.exceptions.CarlCommandException;
import carl.exceptions.CarlException;
import carl.exceptions.CarlUnknownTaskException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class TaskList {

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    private List<Task> tasks;

    public void addTaskToList(Task task) {
        tasks.add(task);
    }

    public String toSaveString() {
        StringBuilder sb = new StringBuilder();

        for (Task task : tasks) {
            sb.append(task.toSaveFormat()).append(System.lineSeparator()); // System.lineSeparator = \n but work for all OS
        }

        return sb.toString();
    }

    public int getTasksLeft() {
        return tasks.size();
    }

    public List<Task> getAllTasks() {

        return new ArrayList<>(this.tasks);
    }

    public List<Task> getTasksDueOn(LocalDate date) {

        List<Task> tasks = new ArrayList<>();

        for (Task task : this.tasks) {
            if (task.isDueOn(date)) {
                tasks.add(task);
            }
        }

        return tasks;
    }

    public List<Task> findTask(String keyword) {

        List<Task> tasks = new ArrayList<>();

        for (Task task : this.tasks) {
            if (task.hasNameMatch(keyword)) {
                tasks.add(task);
            }
        }

        return tasks;
    }

    public Task markTaskAsDone(int index) throws CarlCommandException {

        if (index < 0 || index >= tasks.size()) {
            throw new CarlCommandException("Invalid task number! Check \"list\"!");
        }

        Task task = tasks.get(index);

        if (!task.markAsDone()) {
            throw new CarlCommandException("Task is already marked as done, cannot be marked again!");
        }


        return task;

    }

    public Task deleteTask(int index) throws CarlException {

        if (index >= tasks.size()) {
            throw new CarlUnknownTaskException();
        }

        Task task = tasks.get(index);

        if (task == null) {
            throw new CarlUnknownTaskException();
        }

        tasks.remove(index);

        return task;

    }

    public Task markTaskAsUndone(int index) throws CarlCommandException {

        if (index < 0 || index >= tasks.size()) {
            throw new CarlCommandException("Invalid task number! Check \"list\"!");
        }

        Task task = tasks.get(index);

        if (!task.unMarkAsDone()) {

            throw new CarlCommandException("Task is already not done, cannot be unmarked!");
        }

        return task;



    }
}
