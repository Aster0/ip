public interface Command {


    public void onRun(Carl carl, String[] args, String raw) throws CarlException;
}
