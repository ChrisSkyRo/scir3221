package tasks.services;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import tasks.model.ArrayTaskList;
import tasks.model.Task;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(MockitoJUnitRunner.class)
class Lab4TasksServiceIntegrationRTest {
    @InjectMocks
    private ArrayTaskList repo;

    private TasksService service;

    private ArrayList<Task> tasks = new ArrayList<>();

    private ObservableList observableList;

    @BeforeEach
    void setUp() {
        tasks.add(Mockito.mock(Task.class));
        Mockito.when(tasks.get(0).getTitle()).thenReturn("t1");
        tasks.add(Mockito.mock(Task.class));
        Mockito.when(tasks.get(1).getTitle()).thenReturn("t2");
        tasks.add(Mockito.mock(Task.class));
        Mockito.when(tasks.get(2).getTitle()).thenReturn("t3");
        tasks.add(Mockito.mock(Task.class));
        Mockito.when(tasks.get(3).getTitle()).thenReturn("t4");

        repo = new ArrayTaskList();
        repo.add(tasks.get(0));
        repo.add(tasks.get(1));
        repo.add(tasks.get(2));
        repo.add(tasks.get(3));
        service = new TasksService(repo);

        observableList = FXCollections.observableList((ArrayList)tasks);
    }

    @AfterEach
    void tearDown() {

    }


    @Test
    void shouldReturnAllTasksFromRepository() {
        ArrayList<Task> expected = tasks;
        ArrayList<Task> result = (ArrayList<Task>) service.getAllTasks();
        assertEquals(expected.size(), result.size());
    }

    @Test
    void shouldReturnObservableList() {
        ObservableList<Task> expected = observableList;
        ObservableList<Task> result = service.getObservableList();
        assertEquals(expected.size(), result.size());
    }
}