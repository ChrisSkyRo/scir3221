package tasks.services;

import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import tasks.model.ArrayTaskList;
import tasks.model.Task;
import tasks.model.TasksOperations;

import java.util.Arrays;
import java.util.Date;

import static org.junit.Assert.assertEquals;

class Lab4TasksServiceIntegrationETest {
    @Test
    void testGetObservableList() {
        ArrayTaskList mockTaskList = Mockito.mock(ArrayTaskList.class);

        Task task1 = new Task("Task 1", new Date());
        Task task2 = new Task("Task 2", new Date());

        Mockito.when(mockTaskList.getAll()).thenReturn(Arrays.asList(task1, task2));

        TasksService tasksService = new TasksService(mockTaskList);

        ObservableList<Task> observableList = tasksService.getObservableList();

        assert(observableList.contains(task1));
        assert(observableList.contains(task2));
    }

    @Test
    void testGetIntervalInHours() {
        Task task = new Task("Repeat Task", new Date(), new Date(), 3600); // 1 hour repeat interval

        TasksService tasksService = new TasksService(new ArrayTaskList());

        String interval = tasksService.getIntervalInHours(task);

        assertEquals("01:00", interval);
    }

    @Test
    void testFilterTasks() {
        Date startDate = new Date();
        Date endDate = new Date(System.currentTimeMillis() + 86400000); // Tomorrow
        Date filterStartDate = new Date(System.currentTimeMillis() - 1000);
        Date filterEndDate = new Date(endDate.getTime() + 1000);

        ArrayTaskList mockTaskList = Mockito.mock(ArrayTaskList.class);

        Task task1 = new Task("Task 1", startDate);
        Task task2 = new Task("Task 2", endDate);
        Task task3 = new Task("Task 3", new Date(endDate.getTime() + 3600000));
        task1.setActive(true);
        task2.setActive(true);
        task3.setActive(true);

        Mockito.when(mockTaskList.getAll()).thenReturn(Arrays.asList(task1, task2, task3));

        TasksOperations mockTasksOperations = Mockito.mock(TasksOperations.class);
        Mockito.when(mockTasksOperations.incoming(startDate, endDate)).thenReturn(Arrays.asList(task1, task2));

        TasksService tasksService = new TasksService(mockTaskList);

        Iterable<Task> filteredTasks = tasksService.filterTasks(filterStartDate, filterEndDate);

        assert (containsTask(filteredTasks, task1));
        assert (containsTask(filteredTasks, task2));
        assert (!containsTask(filteredTasks, task3));
    }

    private boolean containsTask(Iterable<Task> tasks, Task task) {
        for (Task t : tasks) {
            if (t.equals(task)) {
                return true;
            }
        }
        return false;
    }


}