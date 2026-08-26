public interface Command {


    public void onRun(Ui ui, TaskManager storage, TaskList tasks,
                      String[] args, String raw) throws CarlException;


    default public boolean isExited() {
        return false; // if the progarm must be terminated via ByeCommand
    }
}
