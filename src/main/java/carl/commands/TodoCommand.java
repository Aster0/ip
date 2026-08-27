package carl.commands;

import carl.exceptions.CarlCommandException;
import carl.exceptions.CarlException;
import carl.task.*;
import carl.ui.Ui;

import java.util.Arrays;

public class TodoCommand extends AddTaskCommand {

    public TodoCommand(String name) {
        super(name);
    }


    @Override
    public void onRun(Ui ui, TaskManager storage, TaskList tasks, String[] args, String raw) throws CarlException {
        Task task = new Todo(new Item(name));
        tasks.addTaskToList(task);
        super.onRun(ui, storage, tasks, args, raw);
        ui.showAddTask(task, tasks.getTasksLeft());
    }
}
