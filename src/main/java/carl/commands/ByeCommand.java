package carl.commands;

import carl.task.TaskList;
import carl.task.TaskManager;
import carl.ui.Ui;

/**
 * Represents a command to exit and terminate the chatbot session.
 */
public class ByeCommand implements Command {

    /**
     * {@inheritDoc}
     * Displays the farewell message to the user.
     *
     * @param ui      User interface for displaying messages.
     * @param storage Task manager handling task data persistence.
     * @param tasks   List of current tasks.
     * @param raw     Raw input string entered by the user.
     * @return
     */
    @Override
    public CommandResult onRun(Ui ui, TaskManager storage, TaskList tasks, String raw) {
        return CommandResult.exit(ui.showGoodbye());
    }

    /**
     * Returns whether this command signals the application to exit.
     *
     * @return True, indicating the application should terminate.
     */
    @Override
    public boolean isExited() {
        return true; // if carl.commands.ByeCommand is used
    }
}
