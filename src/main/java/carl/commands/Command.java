package carl.commands;

import carl.exceptions.CarlException;
import carl.task.TaskList;
import carl.task.TaskManager;
import carl.ui.Ui;

/**
 * Represents an executable command within the chatbot.
 */
public interface Command {


    /**
     * Executes the command using the provided application components and arguments.
     *
     * @param ui      User interface for interacting with the user.
     * @param storage Task manager handling task data persistence.
     * @param tasks   List of current tasks.
     * @param raw     Raw input string entered by the user.
     * @return
     * @throws CarlException If an error occurs during command execution.
     */
    CommandResult onRun(Ui ui, TaskManager storage, TaskList tasks,
                        String raw) throws CarlException;


    /**
     * Returns whether this command signals the application to terminate.
     * Defaults to false.
     *
     * @return True if the application should terminate, false otherwise.
     */
    default boolean isExited() {
        return false; // if the progarm must be terminated via carl.commands.ByeCommand
    }
}
