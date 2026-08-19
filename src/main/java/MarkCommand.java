public class MarkCommand implements Command {


    @Override
    public void onRun(Carl carl, String[] args, String raw) throws CarlException {
        if (args.length < 2) {
            throw new CarlEmptyCommandException("mark <number> -" +
                    " you can find the number from saying \"list\"!");
        }

        // mark x
        int index = Integer.parseInt(args[1]);
        carl.completeTask(index - 1);


    }
}
