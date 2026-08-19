public class MarkCommand implements Command {


    @Override
    public void onRun(Carl carl, String[] args) {

        // mark x
        int index = Integer.parseInt(args[1]);
        carl.completeTask(index - 1);


    }
}
