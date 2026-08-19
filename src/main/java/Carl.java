import java.util.*;

public class Carl {


    private final String BOT_NAME = "CARL";
    private String separator = "____________________________________________________________";
    private boolean isRunning = true;

    private Map<String, Command> commands = new HashMap<>();
    private List<Task> tasks = new ArrayList<>(100);

    public static void main(String[] args) {
        Carl bot = new Carl();
        bot.start();

    }

    private void initCommands() {
        commands.put("bye", new ByeCommand());
        commands.put("list", new ListCommand());
        commands.put("mark", new MarkCommand());
        commands.put("unmark", new UnmarkCommand());
        commands.put("deadline", new DeadlineCommand());
        commands.put("event", new EventCommand());
        commands.put("todo", new TodoCommand());
    }

    private void start() {
        initCommands();

        String banner = "  ____    _    ____  _     \n"
                + " / ___|  / \\  |  _ \\| |    \n"
                + "| |     / _ \\ | |_) | |    \n"
                + "| |___ / ___ \\|  _ <| |___ \n"
                + " \\____/_/   \\_\\_| \\_\\____|\n";


        System.out.println(separator);
        System.out.println(banner);

        System.out.println("Hello there!  I am " + BOT_NAME + ".");
        System.out.println("What do you need help in?");

        try (Scanner scanner = new Scanner(System.in)) { // added to make sure scanner closes (Closeable interface) after try block


            while (isRunning) {
                String input = scanner.nextLine().trim();

                String[] args = input.split(" ");
                Command command = commands.getOrDefault(args[0].toLowerCase(), new UnknownCommand());
                command.onRun(this, args);

            }
        }
    }

    public void stop() {
        this.isRunning = false;
    }

    public void addItemToList(Item item) {
        tasks.add(new Task(item));
    }

    public void addTaskToList(Task task) {
        tasks.add(task);

        System.out.println(separator);
        System.out.println("Okay! I have added this task: \n  " + task + "\nNow you have " + tasks.size() + " tasks in the list.");
        System.out.println(separator);

    }

    public void listItems() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    public void completeTask(int index) {

        Task task = tasks.get(index);
        if (task != null) {
            task.markAsDone();

            System.out.println("Successfully marked this task as done! \n  " + task);
        }

    }

    public void revertTask(int index) {
        Task task = tasks.get(index);
        if (task != null) {
            task.unMarkAsDone();

            System.out.println("Successfully unmarked this task as not done! \n  " + task);
        }
    }


}