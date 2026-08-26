public class DeleteCommand extends ModifyTaskCommand {


    @Override
    public void onRun(Ui ui, TaskManager storage, TaskList tasks, String[] args, String raw) throws CarlException {
        if (args.length < 2) {
            throw new CarlCommandException("delete <number> -" +
                    " you can find the number from saying \"list\"!");
        }

        // mark x
        int index = Integer.parseInt(args[1]);
        Task task = tasks.deleteTask(index - 1);
        ui.showDeleteTask(task, tasks.getTasksLeft());

        super.onRun(ui, storage, tasks, args, raw);


    }
}
