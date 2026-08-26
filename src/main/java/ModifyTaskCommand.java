public class ModifyTaskCommand implements Command {
    @Override
    public void onRun(Ui ui, TaskManager storage, TaskList tasks, String[] args, String raw) throws CarlException {
        storage.saveAll(tasks);
    }
}
