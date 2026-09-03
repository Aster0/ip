package carl;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import carl.commands.Command;
import carl.commands.CommandResult;
import carl.exceptions.CarlException;
import carl.parser.CarlParser;
import carl.task.TaskList;
import carl.task.TaskManager;
import carl.ui.MainApplication;
import carl.ui.Ui;
import javafx.application.Application;

/**
 * Represents the main entry point and controller of the Carl chatbot application.
 */
public class Carl {


    public static final String BOT_NAME = "CARL";
    private boolean isRunning = true;

    private Map<String, Command> commands = new HashMap<>();
    private TaskManager taskManager;
    private TaskList tasks;
    private Ui ui;
    private CarlParser parser;

    /**
     * Main method to launch the Carl application.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        Application.launch(MainApplication.class, args);
    }

    public String getResponse(String input) {
        try {
            Command command = parser.parseCommands(input);
            System.out.println("Parse");

            return command.onRun(ui, taskManager, tasks, input).message();

        } catch (CarlException e) {
            return e.getMessage();
        }
    }



    public void start() {

        ui = new Ui();
        taskManager = new TaskManager();
        tasks = new TaskList(taskManager.createSave());

        ui.showWelcome();

        parser = new CarlParser();

//        try (Scanner scanner = new Scanner(System.in)) {
//            // added to make sure scanner closes (Closeable interface)
//            // after try block
//
//
//            while (isRunning) {
//                String input = scanner.nextLine().trim();
//
//                try {
//                    Command command = parser.parseCommands(input, ui, taskManager, tasks);
//                    if (command.isExited()) {
//                        stop();
//                    }
//                } catch (CarlException e) {
//                    System.out.println(e.getMessage());
//                }
//
//            }
//        }
    }

    private void stop() {
        this.isRunning = false;
    }




}
