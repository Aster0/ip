import java.util.Arrays;

public class TodoCommand implements Command {


    @Override
    public void onRun(Carl carl, String[] args) {

        String name = String.join(" ", Arrays.copyOfRange(args, 1, args.length));
        carl.addTaskToList(new Todo(new Item(name)));



    }
}
