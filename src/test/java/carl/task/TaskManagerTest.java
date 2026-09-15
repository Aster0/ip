package carl.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import carl.commands.TodoCommand;
import carl.exceptions.CarlStorageException;
import carl.ui.Ui;

public class TaskManagerTest {
    @TempDir
    private Path temporaryDirectory;

    @Test
    public void createSave_missingFile_createsEmptySaveFile() {
        Path saveFile = temporaryDirectory.resolve("data/save.txt");
        TaskManager manager = new TaskManager(saveFile);

        List<Task> tasks = manager.createSave();

        assertTrue(Files.exists(saveFile));
        assertTrue(tasks.isEmpty());
        assertEquals(null, manager.getStartupWarning());
    }

    @Test
    public void loadSave_malformedAndDuplicateLines_skipsOnlyInvalidLines() throws Exception {
        Path saveFile = temporaryDirectory.resolve("save.txt");
        Files.writeString(saveFile, """
                T | 0 | valid task
                D | 0 | impossible date | 2026-02-30 1800
                T | 1 | valid task
                E | 0 | zero length | 2026-10-01 1800 | 2026-10-01 1800
                """, StandardCharsets.UTF_8);
        TaskManager manager = new TaskManager(saveFile);

        List<Task> tasks = manager.loadSave();

        assertEquals(1, tasks.size());
        assertNotNull(manager.getStartupWarning());
        assertTrue(manager.getStartupWarning().contains("[2, 3, 4]"));
    }

    @Test
    public void saveAll_validTaskList_replacesFile() throws Exception {
        Path saveFile = temporaryDirectory.resolve("save.txt");
        TaskManager manager = new TaskManager(saveFile);
        TaskList tasks = new TaskList(List.of(new Todo("write tests")));

        manager.saveAll(tasks);

        assertEquals(tasks.toSaveString(), Files.readString(saveFile, StandardCharsets.UTF_8));
        assertFalse(Files.readString(saveFile, StandardCharsets.UTF_8).isBlank());
    }

    @Test
    public void saveAll_destinationIsDirectory_throwsStorageException() {
        TaskManager manager = new TaskManager(temporaryDirectory);
        TaskList tasks = new TaskList(List.of(new Todo("write tests")));

        assertThrows(CarlStorageException.class, () -> manager.saveAll(tasks));
    }

    @Test
    public void modifyingCommand_saveFails_restoresTaskList() {
        TaskManager manager = new TaskManager(temporaryDirectory);
        TaskList tasks = new TaskList(List.of());
        TodoCommand command = new TodoCommand("write tests");

        assertThrows(CarlStorageException.class, () ->
                command.onRun(new Ui(), manager, tasks, "todo write tests"));
        assertTrue(tasks.getAllTasks().isEmpty());
    }
}
