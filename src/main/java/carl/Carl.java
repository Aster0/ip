package carl;


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

    /**
     * Generates the welcome message for the user interface.
     */
    public String getWelcomeMessage() {
        return ui.showWelcome();
    }

    /**
     * Processes user input and returns the result of the executed command.
     *
     * @param input The raw input string entered by the user.
     * @return The result of executing the parsed command, or an error result if parsing/execution fails.
     */
    public CommandResult getResponse(String input) {
        try {
            Command command = parser.parseCommands(input);

            return command.onRun(ui, taskManager, tasks, input);

        } catch (CarlException e) {
            return CommandResult.error(e.getMessage());
        }
    }

    /**
     * Initializes the core dependencies and services required for the chatbot to run.
     */
    public void start() {

        ui = new Ui();
        taskManager = new TaskManager();
        tasks = new TaskList(taskManager.createSave());

        parser = new CarlParser();
    }




}
