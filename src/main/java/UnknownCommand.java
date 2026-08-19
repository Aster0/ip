import java.util.Arrays;

public class UnknownCommand implements Command {


    @Override
    public void onRun(Carl carl, String[] args) {

        String name = String.join(" ", Arrays.copyOfRange(args, 0, args.length));
        carl.addTaskToList(new Task(new Item(name)));

    }
}
