package carl.ui;

import java.util.List;

import carl.Carl;
import carl.task.Task;


/**
 * Represents the user interface for the application.
 * Handles displaying messages to the user.
 */
public class Ui {
    public static final String SEPARATOR = "____________________________________________________________";


    /**
     * Displays the welcome message and banner to the user.
     */
    public void showWelcome() {
        String banner = "  ____    _    ____  _     \n" // Used Gemini to create this Ascii Banner
                + " / ___|  / \\  |  _ \\| |    \n"
                + "| |     / _ \\ | |_) | |    \n"
                + "| |___ / ___ \\|  _ <| |___ \n"
                + " \\____/_/   \\_\\_| \\_\\____|\n";


        System.out.println(SEPARATOR);
        System.out.println(banner);

        System.out.println("Hello there!  I am " + Carl.BOT_NAME + ".");
        System.out.println("What do you need help in?");
    }

    /**
     * Displays the goodbye message.
     */
    public void showGoodbye() {
        System.out.println("Bye bye! Hope to see you again soon!");
    }

    /**
     * Displays a message confirming a task has been added.
     *
     * @param task  The task that was added.
     * @param count The total number of tasks in the list.
     */
    public void showAddTask(Task task, int count) {

        System.out.println(SEPARATOR);
        System.out.println("Okay! I have added this task: \n  " + task + "\n" + tasksLeft(count));
        System.out.println(SEPARATOR);
    }

    private String tasksLeft(int count) {
        return "\nNow you have " + count + " tasks in the list.";
    }

    /**
     * Displays a message confirming a task has been marked as done.
     *
     * @param task The task that was marked as done.
     */
    public void showMarkTaskAsDone(Task task) {
        System.out.println("Done! Task is marked as done!\n  " + task);
    }

    /**
     * Displays a message confirming a task has been unmarked.
     *
     * @param task The task that was unmarked.
     */
    public void showUnMarkTask(Task task) {
        System.out.println("Successfully unmarked this task as not done! \n  " + task);
    }

    /**
     * Displays a message confirming a task has been deleted.
     *
     * @param task  The task that was deleted.
     * @param count The remaining number of tasks in the list.
     */
    public void showDeleteTask(Task task, int count) {
        System.out.println("Successfully removed this task! \n  " + task + "\n" + tasksLeft(count));
    }

    /**
     * Displays the list of tasks to the user.
     *
     * @param tasks The list of tasks to display.
     */
    public void showTaskList(List<Task> tasks) {

        int i = 1;

        if (tasks.isEmpty()) {
            System.out.println("No tasks found.");
            return;
        }

        for (Task task : tasks) {
            System.out.println(i++ + ". " + task);
        }
    }
}
