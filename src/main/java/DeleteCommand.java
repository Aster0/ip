public class DeleteCommand implements Command {


    @Override
    public void onRun(Carl carl, TaskManager storage, TaskList tasks, String[] args, String raw) throws CarlException {
        if (args.length < 2) {
            throw new CarlEmptyCommandException("delete <number> -" +
                    " you can find the number from saying \"list\"!");
        }

        // mark x
        int index = Integer.parseInt(args[1]);
        tasks.deleteTask(index - 1);

    }
}
