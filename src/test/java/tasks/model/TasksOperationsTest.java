package tasks.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class TasksOperationsTest {
    static TasksOperations tasksOperations;

    @BeforeEach
    void setUp() {
        ArrayList<Task> al = new ArrayList<>();
        al.add(new Task("t1", new Date(2024, 10, 9)));
        al.add(new Task("t2", new  Date(2024, 10, 10)));
        al.add(new Task("t3", new  Date(2024, 10, 13),new  Date(2024, 10, 15),3600));
        al.add(new Task("t4", new  Date(2024, 10, 10),new  Date(2024, 10, 15),3600));
        al.forEach(t->t.setActive(true));
        ObservableList<Task> list = FXCollections.observableList(al);

        tasksOperations = new TasksOperations(list);
    }

    @AfterAll
    static void afterAll() {
        tasksOperations= null;
    }

    @BeforeAll
    static void beforeAll() {

    }

    @AfterEach
    void tearDown() {
        tasksOperations = null;
    }

    @Test
    void Test1() {
        //arrange
        //act
        try {
            ArrayList result = (ArrayList) tasksOperations.incoming(null, null);
            //assert
            assert (false);
        } catch (NullPointerException ex) {
            assert (true);
        }
    }

    @Test
    void Test2() {

        //arrange
        tasksOperations.tasks.clear();
        //act
        ArrayList result = (ArrayList) tasksOperations.incoming(null,null);
        //assert
        assertEquals(0,result.size());

    }

    @Test
    void Test3() {
        //arrange

        //act
        ArrayList result = (ArrayList) tasksOperations.incoming(new Date(2024,10,11),new Date(2024,10,12));
        //assert
        assertEquals(1,result.size());

    }

    @Test
    void Test4() {
        //arrange

        //act
        ArrayList result = (ArrayList) tasksOperations.incoming(new Date(2024,10,8),new  Date(2024, 10, 9));
        //assert
        assertEquals(1,result.size());

    }
    @Test
    void Test5() {
        //arrange

        //act
        ArrayList result = (ArrayList) tasksOperations.incoming(new Date(2024,10,6),new  Date(2024, 10, 7));
        //assert
        assertEquals(0,result.size());

    }
}