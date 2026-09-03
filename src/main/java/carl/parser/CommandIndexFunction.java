package carl.parser;

import carl.commands.Command;

@FunctionalInterface
public interface CommandIndexFunction<T extends Command> {
    T create(int index);
}
