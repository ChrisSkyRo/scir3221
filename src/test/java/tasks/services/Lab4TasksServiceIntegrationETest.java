package tasks.services;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import tasks.model.ArrayTaskList;
import tasks.model.Task;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class Lab4TasksServiceIntegrationETest {
    private ArrayTaskList repo;

    private TasksService service;

    private ArrayList<Task> tasks = new ArrayList<>();

    private ObservableList observableList;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        tasks.add(new Task("t1", new Date(System.currentTimeMillis())));
        tasks.add(new Task("t2", new Date(System.currentTimeMillis())));
        tasks.add(new Task("t3", new Date(System.currentTimeMillis())));
        tasks.add(new Task("t4", new Date(System.currentTimeMillis())));

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
    void integration_test_getAllTasks() {

        ArrayList<Task> expected = tasks;
        ArrayList<Task> result = (ArrayList<Task>) service.getAllTasks();

        assertEquals(expected.size(), result.size());
    }

    @Test
    void integration_test_getObservableList() {

        ObservableList<Task> expected = observableList;
        ObservableList<Task> result = service.getObservableList();

        assertEquals(expected.size(), result.size());
    }
}