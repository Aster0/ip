public class UnknownCommand implements Command {


    @Override
    public void onRun(Carl carl, String[] args) {

        System.out.println(args[0]); // jsut repeat whatever user said
    }
}
