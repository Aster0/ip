import java.util.*;

public class Carl {


    private final String BOT_NAME = "CARL";
    public static final String SEPARATOR = "____________________________________________________________";
    private boolean isRunning = true;

    private Map<String, Command> commands = new HashMap<>();
    private TaskManager taskManager;
    private TaskList tasks;

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
        commands.put("delete", new DeleteCommand());
    }

    private void start() {
        initCommands();

        String banner = "  ____    _    ____  _     \n" // Used Gemini to create this Ascii Banner
                + " / ___|  / \\  |  _ \\| |    \n"
                + "| |     / _ \\ | |_) | |    \n"
                + "| |___ / ___ \\|  _ <| |___ \n"
                + " \\____/_/   \\_\\_| \\_\\____|\n";


        taskManager = new TaskManager();
        tasks = new TaskList(taskManager.createSave());

        System.out.println(SEPARATOR);
        System.out.println(banner);

        System.out.println("Hello there!  I am " + BOT_NAME + ".");
        System.out.println("What do you need help in?");

        try (Scanner scanner = new Scanner(System.in)) { // added to make sure scanner closes (Closeable interface) after try block


            while (isRunning) {
                String input = scanner.nextLine().trim();

                try {
                    parseCommands(input);
                }
                catch(CarlException e) {
                    System.out.println(e.getMessage());
                }

            }
        }
    }

    private void parseCommands(String input) throws CarlException{
        String[] args = input.split(" ");
        Command command = commands.get(args[0].toLowerCase());

        if (command == null) {
            throw new CarlUnknownCommandException();
        }

        command.onRun(this, taskManager, this.tasks, args, input);
    }

    public void stop() {
        this.isRunning = false;
    }




}