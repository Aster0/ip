public class ByeCommand implements Command {


    @Override
    public void onRun(Carl carl, String[] args) {

        System.out.println("Bye bye! Hope to see you again soon!");
        carl.stop();
    }
}
