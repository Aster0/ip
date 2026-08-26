public interface Command {


    public void onRun(Carl carl, TaskManager storage, TaskList tasks,
                      String[] args, String raw) throws CarlException;
}
