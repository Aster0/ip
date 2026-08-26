public class UnmarkCommand extends ModifyTaskCommand {
    @Override
    public void onRun(Ui ui, TaskManager storage, TaskList tasks, String[] args, String raw) throws CarlException {

        if (args.length < 2) {
            throw new CarlCommandException("unmark <number> -" +
                    " you can find the number from saying \"list\"!");
        }

        // unmark x
        int index = Integer.parseInt(args[1]);
        Task task = tasks.markTaskAsUndone(index - 1);
        super.onRun(ui, storage, tasks, args, raw);
        ui.showUnMarkTask(task);
    }
}
