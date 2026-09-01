package carl;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import carl.task.Deadline;
import carl.task.Event;
import carl.task.Item;
import carl.task.Task;
import carl.task.TaskList;
import carl.task.Todo;

public class TaskListTest {

    private TaskList taskList;

    @BeforeEach
    public void initial() {
        taskList = new TaskList(new ArrayList<>());
    }

    // using the naming convention: given_when_then
    // https://www.baeldung.com/java-unit-testing-best-practices

    @Test
    public void getAllTasks_multipleTasksAdded_returnsAllTasksInOrder() {

        Deadline deadline = new Deadline(new Item("Deadline 1"),
                LocalDateTime.of(LocalDate.of(2026, 8, 26),
                        LocalTime.of(18, 1)));

        Event event = new Event(new Item("Event 1"),
                LocalDateTime.of(LocalDate.of(2026, 8, 26),
                        LocalTime.of(18, 1)),
                LocalDateTime.of(LocalDate.of(2026, 8, 29),
                        LocalTime.of(18, 1)));

        Todo todo = new Todo(new Item("Todo 1"));

        List<Task> matchList = List.of(deadline, event, todo);

        taskList.addTaskToList(deadline);
        taskList.addTaskToList(event);
        taskList.addTaskToList(todo);

        List<Task> result = taskList.getAllTasks();

        assertEquals(3, result.size());
        assertEquals(matchList, result);

    }

    @Test
    public void getTasksDueOn_mixedDates_returnsOnlyMatchingTasks() {

        Deadline deadline = new Deadline(new Item("Deadline 1"),
                LocalDateTime.of(LocalDate.of(2026, 8, 26),
                        LocalTime.of(18, 1)));

        Event event = new Event(new Item("Event 1"),
                LocalDateTime.of(LocalDate.of(2026, 8, 26),
                        LocalTime.of(18, 1)),
                LocalDateTime.of(LocalDate.of(2026, 8, 29),
                        LocalTime.of(18, 1)));

        Event event2 = new Event(new Item("Event 2"),
                LocalDateTime.of(LocalDate.of(2026, 8, 24),
                        LocalTime.of(18, 1)),
                LocalDateTime.of(LocalDate.of(2026, 8, 30),
                        LocalTime.of(18, 1)));

        Deadline deadline2 = new Deadline(new Item("Deadline 2"),
                LocalDateTime.of(LocalDate.of(2026, 8, 29),
                        LocalTime.of(18, 1)));

        List<Task> matchList = List.of(deadline, event, event2);

        taskList.addTaskToList(deadline);
        taskList.addTaskToList(event);
        taskList.addTaskToList(deadline2);
        taskList.addTaskToList(event2);

        List<Task> result = taskList.getTasksDueOn(LocalDate.of(2026, 8, 26));

        assertEquals(3, result.size());
        assertEquals(matchList, result);

    }

}
