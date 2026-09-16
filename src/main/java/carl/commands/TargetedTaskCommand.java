package carl.commands;

/**
 * Represents a command to add a task to the task list.
 */
public abstract class TargetedTaskCommand extends ModifyTaskCommand {
    /** One-based index of the task to be targeted. */
    protected final int index;

    /**
     * Constructs a task-changing command for the specified one-based index.
     *
     * @param index one-based task index
     */
    public TargetedTaskCommand(int index) {
        this.index = index;
    }
}
