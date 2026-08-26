import java.util.*;

public class Carl {


    public static final String BOT_NAME = "CARL";
    private boolean isRunning = true;

    private Map<String, Command> commands = new HashMap<>();
    private TaskManager taskManager;
    private TaskList tasks;
    private Ui ui;

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
        commands.put("due", new DueCommand());
    }

    private void start() {
        initCommands();
        ui = new Ui();
        taskManager = new TaskManager();
        tasks = new TaskList(taskManager.createSave());

        ui.showWelcome();

        try (Scanner scanner = new Scanner(System.in)) { // added to make sure scanner closes (Closeable interface) after try block


            while (isRunning) {
                String input = scanner.nextLine().trim();

                try {
                    Command command = parseCommands(input);
                    if (command.isExited()) {
                        stop();
                    }
                }
                catch(CarlException e) {
                    System.out.println(e.getMessage());
                }

            }
        }
    }

    private Command parseCommands(String input) throws CarlException{
        String[] args = input.split(" ");
        Command command = commands.get(args[0].toLowerCase());

        if (command == null) {
            throw new CarlUnknownCommandException();
        }

        command.onRun(ui, taskManager, this.tasks, args, input);

        return command;
    }

    private void stop() {
        this.isRunning = false;
    }




}