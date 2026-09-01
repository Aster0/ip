package carl.task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import carl.util.DateParser;

/**
 * Manages loading and saving tasks to the local storage disk.
 */
public class TaskManager {

    private static final String SAVE_FILE_PATH = "./save.txt";
    /**
     * Loads the saved tasks from the storage file.
     *
     * @return A list of tasks loaded from the file.
     */
    public List<Task> loadSave() {

        File file = new File("./save.txt");
        List<Task> tasks = new ArrayList<Task>();

        try (Scanner scanner = new Scanner(file)) {

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                String[] args = line.split("\\s*\\|\\s*");
                // using regex to ignore empty spaces. i.e., D|1|... will work too

                Task task = parseStringToTask(args);
                tasks.add(task);

            }
        } catch (FileNotFoundException e) {
            System.out.println("Can't load file: " + e.getMessage());
        }

        return tasks;
    }

    private Task parseStringToTask(String[] args) {
        // prefix | status | name | by | to
        Item item = new Item(args[2]);
        String from = args.length > 3 ? args[3] : "Not Indicated";
        String to = args.length > 4 ? args[4] : "Not Indicated";
        LocalDateTime timeFrom;
        LocalDateTime timeTo;

        timeFrom = parseStringToDate(from);
        timeTo = parseStringToDate(to);

        Task.TaskData data = new Task.TaskData(TaskType.of(args[0]),
                args[1].equals("1") ? TaskStatus.DONE : TaskStatus.NOT_DONE,
                item, timeFrom, timeTo);

        return Task.of(data);
    }

    private LocalDateTime parseStringToDate(String date) {
        LocalDateTime time;
        try {
            time = DateParser.dateParser(date);
        } catch (DateTimeParseException e) {
            time = LocalDateTime.now();
        }

        return time;
    }

    /**
     * Saves all current tasks in the TaskList to the storage file.
     *
     * @param tasks The task list containing tasks to be saved.
     */
    public void saveAll(TaskList tasks) {
        try (FileWriter writer = new FileWriter(SAVE_FILE_PATH)) {
            writer.write(tasks.toSaveString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Creates a new save file if it doesn't exist, or loads data if it does.
     *
     * @return A list of tasks (empty if a new file is created, otherwise populated from the existing file).
     */
    public List<Task> createSave() {
        File file = new File(SAVE_FILE_PATH);
        try {
            if (!file.createNewFile()) {
                return loadSave();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ArrayList<Task>();

    }


}
