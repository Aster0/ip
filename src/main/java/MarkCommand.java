public class MarkCommand extends ModifyTaskCommand {


    @Override
    public void onRun(Ui ui, TaskManager storage, TaskList tasks, String[] args, String raw) throws CarlException {
        if (args.length < 2) {
            throw new CarlCommandException("mark <number> -" +
                    " you can find the number from saying \"list\"!");
        }

        // mark x
        int index = Integer.parseInt(args[1]);
        Task task = tasks.markTaskAsDone(index - 1);
        super.onRun(ui, storage, tasks, args, raw);
        ui.showMarkTaskAsDone(task);

    }
}
