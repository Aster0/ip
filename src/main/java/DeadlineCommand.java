import java.util.Arrays;

public class DeadlineCommand implements Command {


    @Override
    public void onRun(Carl carl, String[] args, String raw) throws CarlException {

        if (args.length < 2 || !raw.contains("/by")) {
            throw new CarlEmptyCommandException("deadline <project_name> /by <date>");
        }

        int byIndex = getByIndex(args);
        String name = String.join(" ", Arrays.copyOfRange(args, 1, byIndex)).trim();
        String by = String.join(" ", Arrays.copyOfRange(args, byIndex + 1, args.length)).trim();

        validateNonEmpty(name, by);
        carl.addTaskToList(new Deadline(new Item(name), by));
    }

    private void validateNonEmpty(String name, String by) throws CarlException {
        if (by.isEmpty() || name.isEmpty()) {
            throw new CarlEmptyCommandException("deadline <project_name> /by <date>");
        }

    }

    private int getByIndex(String[] args) {
        int byIndex = -1;

        for (int i = 0; i < args.length; i++) {
            String str = args[i];

            if (str.equals("/by")) {
                byIndex = i;
            }
        }
        if (byIndex == -1) {
            byIndex = args.length;
        }
        return byIndex;
    }
}
