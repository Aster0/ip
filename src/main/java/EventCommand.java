import java.util.Arrays;

public class EventCommand extends ModifyTaskCommand {

    private static final String USAGE = "event <project_name> /from <date> /to <date>";

    @Override
    public void onRun(Carl carl, TaskManager storage, TaskList tasks, String[] args, String raw) throws CarlException {
        if (args.length < 2 || !raw.contains("/from") || !raw.contains("/to")) {
            throw new CarlEmptyCommandException(USAGE);
        }

        int fromIndex = getIndexOf(args, "/from");
        int toIndex = getIndexOf(args, "/to");

        if (fromIndex > toIndex) {
            throw new CarlEmptyCommandException(USAGE);
        }

        String name = String.join(" ", Arrays.copyOfRange(args, 1, fromIndex)).trim();
        String from = String.join(" ", Arrays.copyOfRange(args, fromIndex + 1, toIndex)).trim();
        String to = String.join(" ", Arrays.copyOfRange(args, toIndex + 1, args.length)).trim();

        validateNonEmpty(name, from, to);

        tasks.addTaskToList(new Event(new Item(name), from, to));
        super.onRun(carl, storage, tasks, args, raw);
    }

    private int getIndexOf(String[] args, String word) {
        int byIndex = -1;

        for (int i = 0; i < args.length; i++) {
            String str = args[i];

            if (str.equals(word)) {
                byIndex = i;
            }
        }
        if (byIndex == -1) {
            byIndex = args.length;
        }
        return byIndex;
    }

    private void validateNonEmpty(String name, String from, String to) throws CarlException {
        if (name.isEmpty() || from.isEmpty() || to.isEmpty()) {
            throw new CarlEmptyCommandException(USAGE);
        }
    }
}
