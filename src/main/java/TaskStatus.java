public enum TaskStatus {

    NOT_DONE("[ ]"),
    DONE("[X]");

    private final String prefix;

    TaskStatus(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public String toString() {
        return prefix;
    }

    public int toInt() {
        return switch (this) {
            case DONE -> 1;
            case NOT_DONE -> 0;
        };
    }
}
