package carl.ui;

import carl.Carl;
import carl.task.Task;
import carl.task.TaskList;

import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicInteger;

public class Ui {
    public static final String SEPARATOR = "____________________________________________________________";


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

    public void showGoodbye() {
        System.out.println("Bye bye! Hope to see you again soon!");
    }

    public void showAddTask(Task task, int count) {

        System.out.println(SEPARATOR);
        System.out.println("Okay! I have added this task: \n  " + task + "\n" + tasksLeft(count));
        System.out.println(SEPARATOR);
    }

    private String tasksLeft(int count) {
        return "\nNow you have " + count + " tasks in the list.";
    }

    public void showMarkTaskAsDone(Task task) {
        System.out.println("Done! carl.task.Task is marked as done!\n" + task);
    }

    public void showUnMarkTask(Task task) {
        System.out.println("Successfully unmarked this task as not done! \n  " + task);
    }

    public void showDeleteTask(Task task, int count) {
        System.out.println("Successfully removed this task! \n  " + task + "\n" + tasksLeft(count));
    }

    public void showTaskList(TaskList tasks) {

        AtomicInteger i = new AtomicInteger();
        tasks.forEach((task) -> {
            System.out.println(i.getAndIncrement() + 1 + ". " + task);
        });
    }

    public void showTaskListDueOn(TaskList tasks, LocalDate time) {

        AtomicInteger i = new AtomicInteger();
        tasks.forEach((task) -> {
            if (task.isDueOn(time)) {
                System.out.println(i.getAndIncrement() + 1 + ". " + task);
            }
        });
    }
}
