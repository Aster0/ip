package carl.ui;


import java.util.List;
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
    public String showWelcome() {
        return "  ____    _    ____  _     \n" // Used Gemini to create this Ascii Banner
                + " / ___|  / \\  |  _ \\| |    \n"
                + "| |     / _ \\ | |_) | |    \n"
                + "| |___ / ___ \\|  _ <| |___ \n"
                + " \\____/_/   \\_\\_| \\_\\____|\n" +
                "" +
                "" + SEPARATOR + "\n" +
                "\"Hello there!  I am \" + Carl.BOT_NAME + \".\"" +
                "\nWhat do you need help in?";

    }

    /**
     * Displays the goodbye message.
     */
    public String showGoodbye() {
        return "Bye bye! Hope to see you again soon!";
    }

    /**
     * Displays a message confirming a task has been added.
     *
     * @param task  The task that was added.
     * @param count The total number of tasks in the list.
     */
    public String showAddTask(Task task, int count) {

        return SEPARATOR + "\n Okay! I have added this task: \n  "
                + task + "\n" + tasksLeft(count) + "\n" + SEPARATOR;
    }

    private String tasksLeft(int count) {
        return "\nNow you have " + count + " tasks in the list.";
    }

    /**
     * Displays a message confirming a task has been marked as done.
     *
     * @param task The task that was marked as done.
     */
    public String showMarkTaskAsDone(Task task) {
        return "Done! Task is marked as done!\n  " + task;
    }

    /**
     * Displays a message confirming a task has been unmarked.
     *
     * @param task The task that was unmarked.
     */
    public String showUnMarkTask(Task task) {
        return "Successfully unmarked this task as not done! \n  " + task;
    }

    /**
     * Displays a message confirming a task has been deleted.
     *
     * @param task  The task that was deleted.
     * @param count The remaining number of tasks in the list.
     */
    public String showDeleteTask(Task task, int count) {

        return "Successfully removed this task! \n  " + task + "\n" + tasksLeft(count);
    }

    /**
     * Displays the list of tasks to the user.
     *
     * @param tasks The list of tasks to display.
     */
    public String showTaskList(List<Task> tasks) {

        int i = 1;

        if (tasks.isEmpty()) {
            return "No tasks found.";
        }

        String message = "";
        for (Task task : tasks) {
            message += i++ + ". " + task + "\n";
        }

        return message;
    }
}
