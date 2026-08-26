public class ListCommand implements Command {


    @Override
    public void onRun(Carl carl, TaskManager storage, TaskList tasks, String[] args, String raw) {
        tasks.listItems();
    }
}
