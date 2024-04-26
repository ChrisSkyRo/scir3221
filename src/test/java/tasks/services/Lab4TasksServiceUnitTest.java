package tasks.services;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import tasks.model.ArrayTaskList;
import tasks.model.Task;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class Lab4TasksServiceUnitTest {
    @Mock
    private ArrayTaskList repo;

    @InjectMocks
    private TasksService service;

    private ArrayList<Task> tasks = new ArrayList<>();

    private ObservableList<Task> observableList;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        service = new TasksService(repo);

        tasks.add(Mockito.mock(Task.class));
        Mockito.when(tasks.get(0).getTitle()).thenReturn("t1");
        tasks.add(Mockito.mock(Task.class));
        Mockito.when(tasks.get(1).getTitle()).thenReturn("t2");
        tasks.add(Mockito.mock(Task.class));
        Mockito.when(tasks.get(2).getTitle()).thenReturn("t3");
        tasks.add(Mockito.mock(Task.class));
        Mockito.when(tasks.get(3).getTitle()).thenReturn("t4");

        observableList = FXCollections.observableArrayList(tasks);
    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void unit_test_getAllTasks() {
        Mockito.when(repo.getAll()).thenReturn(tasks);

        ArrayList<Task> expected = tasks;
        ArrayList<Task> result = (ArrayList<Task>) service.getAllTasks();

        assertEquals(expected.size(), result.size());
    }

    @Test
    void unit_test_getObservableList() {

        Mockito.when(repo.getAll()).thenReturn(tasks);

        ObservableList<Task> expected = observableList;
        ObservableList<Task> result = service.getObservableList();

        assertEquals(expected.size(), result.size());
    }
}