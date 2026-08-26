import java.util.List;

public class TaskList {

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    private List<Task> tasks;

    public void addTaskToList(Task task) {
        tasks.add(task);

        System.out.println(Carl.SEPARATOR);
        System.out.println("Okay! I have added this task: \n  " + task + "\n" + tasksLeft());
        System.out.println(Carl.SEPARATOR);


    }

    public String toSaveString() {
        StringBuilder sb = new StringBuilder();

        for (Task task : tasks) {
            sb.append(task.toSaveFormat()).append(System.lineSeparator()); // System.lineSeparator = \n but work for all OS
        }

        return sb.toString();
    }

    private String tasksLeft() {
        return "\nNow you have " + tasks.size() + " tasks in the list.";
    }

    public void listItems() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    public void completeTask(int index) {

        if (index < 0 || index >= tasks.size()) {
            System.out.println("Invalid task number! Check \"list\"!");
            return;
        }

        Task task = tasks.get(index);

        if (task.markAsDone()) {
            System.out.println("Successfully marked this task as done! \n  " + task);
            return;
        }

        System.out.println("Task is already marked as done, cannot be marked again!");



    }

    public void deleteTask(int index) throws CarlException {

        if (index >= tasks.size()) {
            throw new CarlUnknownTaskException();
        }

        Task task = tasks.get(index);

        if (task == null) {
            throw new CarlUnknownTaskException();
        }

        tasks.remove(index);
        System.out.println("Successfully removed this task! \n  " + task + "\n" + tasksLeft());

    }

    public void revertTask(int index) {

        if (index < 0 || index >= tasks.size()) {
            System.out.println("Invalid task number! Check \"list\"!");
            return;
        }
        Task task = tasks.get(index);

        if (task.unMarkAsDone()) {
            System.out.println("Successfully unmarked this task as not done! \n  " + task);
            return;
        }
        System.out.println("Task is already not done, cannot be unmarked!");



    }
}
