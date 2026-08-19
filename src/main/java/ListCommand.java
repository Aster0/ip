public class ListCommand implements Command {


    @Override
    public void onRun(Carl carl, String[] args, String raw) {
        carl.listItems();
    }
}
