public class UnmarkCommand implements Command {
    @Override
    public void onRun(Carl carl, String[] args) {
        // unmark x
        int index = Integer.parseInt(args[1]);
        carl.revertTask(index - 1);
    }
}
