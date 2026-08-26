import javax.print.attribute.HashPrintRequestAttributeSet;

public enum TaskType {

    TODO("T"),
    DEADLINE("D"),
    EVENT("E");


    private final String PREFIX;

    TaskType(String prefix) {
        this.PREFIX = prefix;
    }

    @Override
    public String toString() {
        return this.PREFIX;
    }


    public static TaskType of(String prefix) {
        return switch (prefix) {
            case "T" -> TODO;
            case "D" -> DEADLINE;
            case "E" -> EVENT;
            case null, default -> throw new IllegalArgumentException("Invalid task prefix");
        };
    }
}
