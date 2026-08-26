public class ListCommand implements Command {


    @Override
    public void onRun(Ui ui, TaskManager storage, TaskList tasks, String[] args, String raw) {
        ui.showTaskList(tasks);
    }
}
