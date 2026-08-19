import java.util.Arrays;

public class EventCommand implements Command {


    @Override
    public void onRun(Carl carl, String[] args, String raw) throws CarlException {
        if (args.length < 2 || !raw.contains("/from") || !raw.contains("/to")) {
            throw new CarlEmptyCommandException("event <project_name> /from <date> /to <date>");
        }

        // not sure if we need to make it so its dynamic where /to can come first than /from or what but i made both ways work
        int fromIndex = -1;
        int toIndex = -1;

        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("/from")) {
                fromIndex = i;
            } else if (args[i].equals("/to")) {
                toIndex = i;
            }
        }

        String from = "";
        String to = "";

        if (fromIndex != -1) {
            int end = args.length;

            if (toIndex != -1 && toIndex > fromIndex) {
                end = toIndex;
            }

            from = String.join(" ",
                    Arrays.copyOfRange(args, fromIndex + 1, end)
            );
        }

        if (toIndex != -1) {
            int end = args.length;

            if (fromIndex != -1 && fromIndex > toIndex) {
                end = fromIndex;
            }

            to = String.join(" ",
                    Arrays.copyOfRange(args, toIndex + 1, end)
            );
        }

        int nameEnd = args.length;

        for (int i = 1; i < args.length; i++) {
            if (args[i].equals("/from") ||
                    args[i].equals("/to") ||
                    args[i].equals("/by")) {
                nameEnd = i;
                break;
            }
        }

        String name = String.join(" ",
                Arrays.copyOfRange(args, 1, nameEnd));

        carl.addTaskToList(new Event(new Item(name), from, to));



    }
}
