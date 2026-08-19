import java.util.Arrays;

public class DeadlineCommand implements Command {


    @Override
    public void onRun(Carl carl, String[] args) {


        String from = "";
        int fromIndex = -1;

        for (int i = 0; i < args.length; i++) {

            String str = args[i];

            if (str.equals("/by")) {
                from = String.join(" ",
                        Arrays.copyOfRange(args, i + 1, args.length));
                fromIndex = i;
            }
        }

        if (fromIndex == -1) {
            fromIndex = args.length;
        }

        String name = String.join(" ", Arrays.copyOfRange(args, 1, fromIndex));

        carl.addTaskToList(new Deadline(new Item(name), from));



    }
}
