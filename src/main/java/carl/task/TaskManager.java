package carl.task;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.AtomicMoveNotSupportedException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import carl.exceptions.CarlStorageException;
import carl.util.DateParser;

/**
 * Manages loading and saving tasks to the local storage disk.
 */
public class TaskManager {
    private static final String SAVE_FILE_PATH = "./save.txt";
    private static final int MAX_SAVED_TASK_NAME_LENGTH = 500;

    private final Path saveFilePath;
    private String startupWarning;

    /** Creates a task manager that uses Carl's default save-file location. */
    public TaskManager() {
        this(Path.of(SAVE_FILE_PATH));
    }

    /**
     * Creates a task manager using a specific path, primarily for isolated testing.
     *
     * @param saveFilePath path of the task save file
     */
    public TaskManager(Path saveFilePath) {
        this.saveFilePath = Objects.requireNonNull(saveFilePath);
    }

    /**
     * Loads valid saved tasks and skips malformed or duplicate entries.
     *
     * @return valid tasks loaded from the file
     */
    public List<Task> loadSave() {
        List<Task> tasks = new ArrayList<>();
        List<Integer> invalidLineNumbers = new ArrayList<>();
        startupWarning = null;

        try {
            List<String> lines = Files.readAllLines(saveFilePath, StandardCharsets.UTF_8);
            for (int i = 0; i < lines.size(); i++) {
                String line = lines.get(i);
                if (line.isBlank()) {
                    continue;
                }

                try {
                    Task task = parseStringToTask(line);
                    boolean isDuplicate = tasks.stream()
                            .anyMatch(existingTask -> existingTask.hasSameDetails(task));
                    if (isDuplicate) {
                        invalidLineNumbers.add(i + 1);
                    } else {
                        tasks.add(task);
                    }
                } catch (IllegalArgumentException | DateTimeParseException e) {
                    invalidLineNumbers.add(i + 1);
                }
            }
        } catch (IOException | SecurityException e) {
            startupWarning = "Carl could not read `" + saveFilePath
                    + "`. Check that the file exists and is readable. Starting with an empty task list.";
            return tasks;
        }

        if (!invalidLineNumbers.isEmpty()) {
            startupWarning = "Skipped invalid or duplicate saved task data on line(s): "
                    + invalidLineNumbers + ". Valid tasks were loaded normally.";
        }
        return tasks;
    }

    /** Converts one validated save-file line into a task. */
    private Task parseStringToTask(String line) {
        String[] fields = line.split("\\s*\\|\\s*", -1);
        if (fields.length < 3) {
            throw new IllegalArgumentException("Missing saved task fields");
        }

        TaskType type = TaskType.of(fields[0].strip());
        int expectedFieldCount = switch (type) {
            case TODO -> 3;
            case DEADLINE -> 4;
            case EVENT -> 5;
        };
        if (fields.length != expectedFieldCount) {
            throw new IllegalArgumentException("Unexpected saved task fields");
        }

        TaskStatus status = switch (fields[1].strip()) {
            case "0" -> TaskStatus.NOT_DONE;
            case "1" -> TaskStatus.DONE;
            default -> throw new IllegalArgumentException("Invalid task status");
        };
        String name = fields[2].strip();
        if (name.isEmpty() || name.length() > MAX_SAVED_TASK_NAME_LENGTH) {
            throw new IllegalArgumentException("Invalid task description");
        }

        LocalDateTime from = null;
        LocalDateTime to = null;
        if (type == TaskType.DEADLINE || type == TaskType.EVENT) {
            from = DateParser.parseDateTime(fields[3].strip());
        }
        if (type == TaskType.EVENT) {
            to = DateParser.parseDateTime(fields[4].strip());
            if (!from.isBefore(to)) {
                throw new IllegalArgumentException("Invalid event time range");
            }
        }

        return Task.of(new Task.TaskData(type, status, name, from, to));
    }

    /**
     * Saves all tasks using a temporary file so an interrupted write cannot corrupt existing data.
     *
     * @param tasks task list to save
     * @throws CarlStorageException If the destination cannot be written.
     */
    public void saveAll(TaskList tasks) throws CarlStorageException {
        Path absoluteSavePath = saveFilePath.toAbsolutePath();
        Path parentDirectory = absoluteSavePath.getParent();
        Path temporaryFile = null;

        try {
            if (parentDirectory != null) {
                Files.createDirectories(parentDirectory);
            }
            temporaryFile = Files.createTempFile(parentDirectory, ".carl-save-", ".tmp");
            Files.writeString(temporaryFile, tasks.toSaveString(), StandardCharsets.UTF_8);
            moveIntoPlace(temporaryFile, absoluteSavePath);
            temporaryFile = null;
        } catch (IOException | SecurityException e) {
            throw new CarlStorageException(
                    "Carl could not save your changes. Check access to `" + saveFilePath + "` and try again.", e);
        } finally {
            if (temporaryFile != null) {
                try {
                    Files.deleteIfExists(temporaryFile);
                } catch (IOException | SecurityException ignored) {
                    // The original save error is more useful than a temporary-file cleanup failure.
                }
            }
        }
    }

    /** Moves a completed temporary save into place, using an atomic move when supported. */
    private void moveIntoPlace(Path temporaryFile, Path destination) throws IOException {
        try {
            Files.move(temporaryFile, destination,
                    StandardCopyOption.ATOMIC_MOVE, StandardCopyOption.REPLACE_EXISTING);
        } catch (AtomicMoveNotSupportedException e) {
            Files.move(temporaryFile, destination, StandardCopyOption.REPLACE_EXISTING);
        }
    }

    /**
     * Creates the save file when missing, then loads its valid contents.
     *
     * @return loaded tasks, or an empty list when the save file cannot be accessed
     */
    public List<Task> createSave() {
        startupWarning = null;
        try {
            Path absoluteSavePath = saveFilePath.toAbsolutePath();
            Path parentDirectory = absoluteSavePath.getParent();
            if (parentDirectory != null) {
                Files.createDirectories(parentDirectory);
            }
            if (Files.notExists(absoluteSavePath)) {
                Files.createFile(absoluteSavePath);
            }
        } catch (IOException | SecurityException e) {
            startupWarning = "Carl could not create or access `" + saveFilePath
                    + "`. Check the folder permissions. Changes may not be saved.";
            return new ArrayList<>();
        }
        return loadSave();
    }

    /**
     * Returns a recoverable loading warning to show after startup.
     *
     * @return warning text, or {@code null} when storage loaded normally
     */
    public String getStartupWarning() {
        return startupWarning;
    }
}
