public class CarlUnknownTaskException extends CarlException {

    public CarlUnknownTaskException() {
        super("You tried to delete a task number that do not exist :( Try again. Look at list for the list of tasks!");
    }
}
