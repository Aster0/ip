package carl.parser;

import carl.commands.FindCommand;
import carl.exceptions.CarlCommandException;
import carl.exceptions.CarlException;

public class FindCommandParser implements Parser<FindCommand> {
    @Override
    public FindCommand parse(String input) throws CarlException {
        if (input.isEmpty()) {
            throw new CarlCommandException("find <keyword>");
        }


        return new FindCommand(input);
    }
}
