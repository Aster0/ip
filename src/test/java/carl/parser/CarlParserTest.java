package carl.parser;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import carl.commands.TodoCommand;
import carl.exceptions.CarlCommandException;

public class CarlParserTest {
    private CarlParser parser;

    @BeforeEach
    public void setUp() {
        parser = new CarlParser();
    }

    @Test
    public void parseCommands_extraWhitespace_parsesCommand() throws Exception {
        assertInstanceOf(TodoCommand.class, parser.parseCommands("   todo    write   report   "));
    }

    @Test
    public void parseCommands_blankInput_throwsCommandException() {
        assertThrows(CarlCommandException.class, () -> parser.parseCommands("   "));
    }

    @Test
    public void parseCommands_argumentFreeCommandWithExtraArguments_throwsCommandException() {
        assertThrows(CarlCommandException.class, () -> parser.parseCommands("list extra"));
    }

    @Test
    public void parseCommands_repeatedDeadlineMarker_throwsCommandException() {
        assertThrows(CarlCommandException.class, () ->
                parser.parseCommands("deadline report /by 2026-10-01 1800 /by 2026-10-02 1800"));
    }

    @Test
    public void parseCommands_eventWithEqualStartAndEnd_throwsCommandException() {
        assertThrows(CarlCommandException.class, () ->
                parser.parseCommands("event meeting /from 2026-10-01 1800 /to 2026-10-01 1800"));
    }

    @Test
    public void parseCommands_nonExistentDate_throwsCommandException() {
        assertThrows(CarlCommandException.class, () ->
                parser.parseCommands("deadline report /by 2026-02-30 1800"));
    }

    @Test
    public void parseCommands_nonPositiveTaskNumber_throwsCommandException() {
        assertThrows(CarlCommandException.class, () -> parser.parseCommands("mark 0"));
    }
}
