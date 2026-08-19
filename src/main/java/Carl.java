import java.util.*;

public class Carl {


    public final String BOT_NAME = "CARL";
    private boolean isRunning = true;

    private Map<String, Command> commands = new HashMap<>();
    private List<Item> items = new ArrayList<>(100);

    public static void main(String[] args) {
        Carl bot = new Carl();
        bot.start();

    }

    private void initCommands() {
        commands.put("bye", new ByeCommand());
        commands.put("list", new ListCommand());
    }

    private void start() {
        initCommands();

        String banner = "  ____    _    ____  _     \n"
                + " / ___|  / \\  |  _ \\| |    \n"
                + "| |     / _ \\ | |_) | |    \n"
                + "| |___ / ___ \\|  _ <| |___ \n"
                + " \\____/_/   \\_\\_| \\_\\____|\n";

        String separator = "____________________________________________________________";
        System.out.println(separator);
        System.out.println(banner);

        System.out.println("Hello there!  I am " + BOT_NAME + ".");
        System.out.println("What do you need help in?");

        try (Scanner scanner = new Scanner(System.in)) { // added to make sure scanner closes (Closeable interface) after try block


            while (isRunning) {
                String input = scanner.nextLine().trim().toLowerCase();

                Command command = commands.getOrDefault(input, new UnknownCommand());
                command.onRun(this, input.split(" "));

            }
        }
    }

    public void stop() {
        this.isRunning = false;
    }

    public void addItemToList(Item item) {
        items.add(item);
    }

    public void listItems() {
        for (int i = 0; i < items.size(); i++) {
            System.out.println((i + 1) + ". " + items.get(i));
        }
    }


}