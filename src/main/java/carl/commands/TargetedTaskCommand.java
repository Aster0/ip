package carl.commands;

/**
 * Represents a command to add a task to the task list.
 */
public abstract class TargetedTaskCommand extends ModifyTaskCommand {
    /** Integer index of the task to be targeted */
    protected final int index;

    /**
     * Constructs an {@code AddTaskCommand} with the specified task name.
     *
     * @param index index of the Task
     */
    public TargetedTaskCommand(int index) {
        this.index = index;
    }
}
