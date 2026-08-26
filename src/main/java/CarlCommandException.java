public class CarlCommandException extends CarlException {

    public CarlCommandException(String usage) {
        super(String.format("You used this command wrongly! Reason: %s", usage));
    }
}
