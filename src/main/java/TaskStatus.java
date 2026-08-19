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
}
