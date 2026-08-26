public class UnmarkCommand extends ModifyTaskCommand {
    @Override
    public void onRun(Carl carl, TaskManager storage, TaskList tasks, String[] args, String raw) throws CarlException {

        if (args.length < 2) {
            throw new CarlEmptyCommandException("unmark <number> -" +
                    " you can find the number from saying \"list\"!");
        }

        // unmark x
        int index = Integer.parseInt(args[1]);
        tasks.revertTask(index - 1);
        super.onRun(carl, storage, tasks, args, raw);
    }
}
