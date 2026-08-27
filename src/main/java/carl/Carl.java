package carl;

import carl.commands.*;
import carl.exceptions.CarlException;
import carl.exceptions.CarlUnknownCommandException;
import carl.parser.CarlParser;
import carl.task.TaskList;
import carl.task.TaskManager;
import carl.ui.Ui;

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


    private void start() {

        ui = new Ui();
        taskManager = new TaskManager();
        tasks = new TaskList(taskManager.createSave());

        ui.showWelcome();

        CarlParser parser = new CarlParser();

        try (Scanner scanner = new Scanner(System.in)) { // added to make sure scanner closes (Closeable interface) after try block


            while (isRunning) {
                String input = scanner.nextLine().trim();

                try {
                    Command command = parser.parseCommands(input, ui, taskManager, tasks);
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