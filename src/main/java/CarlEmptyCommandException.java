public class CarlEmptyCommandException extends CarlException {

    public CarlEmptyCommandException(String usage) {
        super(String.format("You used this command wrongly! Usage: %s", usage));
    }
}
