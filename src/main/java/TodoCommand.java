import java.util.Arrays;

public class TodoCommand implements Command {


    @Override
    public void onRun(Carl carl, String[] args, String raw) throws CarlException {
        if (args.length < 2) {
            throw new CarlEmptyCommandException("todo <project_name>");
        }

        String name = String.join(" ", Arrays.copyOfRange(args, 1, args.length));
        carl.addTaskToList(new Todo(new Item(name)));



    }
}
