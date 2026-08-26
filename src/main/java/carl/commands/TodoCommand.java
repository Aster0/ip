package carl.commands;

import carl.exceptions.CarlCommandException;
import carl.exceptions.CarlException;
import carl.task.*;
import carl.ui.Ui;

import java.util.Arrays;

public class TodoCommand extends ModifyTaskCommand {


    @Override
    public void onRun(Ui ui, TaskManager storage, TaskList tasks, String[] args, String raw) throws CarlException {
        if (args.length < 2) {
            throw new CarlCommandException("todo <project_name>");
        }

        String name = String.join(" ", Arrays.copyOfRange(args, 1, args.length));

        Task task = new Todo(new Item(name));
        tasks.addTaskToList(task);
        super.onRun(ui, storage, tasks, args, raw);
        ui.showAddTask(task, tasks.getTasksLeft());
    }
}
