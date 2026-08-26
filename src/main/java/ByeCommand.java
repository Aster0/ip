public class ByeCommand implements Command {


    @Override
    public void onRun(Ui ui, TaskManager storage, TaskList tasks, String[] args, String raw) {
        ui.showGoodbye();
    }

    @Override
    public boolean isExited() {
        return true; // if ByeCommand is used
    }
}
