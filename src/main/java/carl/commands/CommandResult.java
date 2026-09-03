package carl.commands;

public record CommandResult(

        String message,
        boolean isExited,
        boolean isError
) {

    public static CommandResult success(String message) {
        return new CommandResult(message, false, false);
    }

    public static CommandResult exit(String message) {
        return new CommandResult(message, true, false);
    }

    public static CommandResult error(String message) {
        return new CommandResult(message, false, true);
    }

}
