import java.util.Arrays;

public class TodoCommand extends ModifyTaskCommand {


    @Override
    public void onRun(Carl carl, TaskManager storage, TaskList tasks, String[] args, String raw) throws CarlException {
        if (args.length < 2) {
            throw new CarlEmptyCommandException("todo <project_name>");
        }

        String name = String.join(" ", Arrays.copyOfRange(args, 1, args.length));
        tasks.addTaskToList(new Todo(new Item(name)));

        super.onRun(carl, storage, tasks, args, raw);

    }
}
