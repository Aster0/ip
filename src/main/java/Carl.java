import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Carl {


    public final String BOT_NAME = "CARL";
    private boolean isRunning = true;

    private Map<String, Command> commands = new HashMap<>();

    public static void main(String[] args) {
        Carl bot = new Carl();
        bot.start();

    }

    private void initCommands() {
        commands.put("bye", new ByeCommand());
    }

    private void start() {
        Scanner scanner = new Scanner(System.in);
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

        while (isRunning) {
            String input = scanner.nextLine().trim().toLowerCase();

            Command command = commands.getOrDefault(input, new UnknownCommand());
            command.onRun(this, input.split(" "));

        }
    }

    public void stop() {
        this.isRunning = false;
    }


}