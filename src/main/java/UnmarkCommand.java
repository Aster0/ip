public class UnmarkCommand implements Command {
    @Override
    public void onRun(Carl carl, String[] args, String raw) throws CarlException {

        if (args.length < 2) {
            throw new CarlEmptyCommandException("unmark <number> -" +
                    " you can find the number from saying \"list\"!");
        }

        // unmark x
        int index = Integer.parseInt(args[1]);
        carl.revertTask(index - 1);
    }
}
