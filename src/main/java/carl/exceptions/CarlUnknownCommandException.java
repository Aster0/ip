package carl.exceptions;

public class CarlUnknownCommandException extends CarlCommandException {

    public CarlUnknownCommandException() {
        super("Whoops! Seems like you have typed an unknown command!");
    }
}
