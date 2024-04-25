package tasks.model;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class Lab4TaskUnitTest {
    long oneDayMillis = 24 * 60 * 60 * 1000;
    int intervalSeconds = 3600;

    @Test
    void whenTaskIsNotRepeated_ShouldReturnTime() {
        //arrange
        Task task = new Task("task1", new Date(System.currentTimeMillis() + oneDayMillis));
        task.setActive(true);
        //act
        Date result = task.nextTimeAfter(new Date(System.currentTimeMillis()));
        //assert
        assertEquals(task.getTime(), result);
    }

    @Test
    void whenTaskIsRepeated_ShouldReturnTimeBetweenIntervals() {
        //arrange
        Date current = new Date(System.currentTimeMillis());
        Date next = new Date(current.getTime() + intervalSeconds * 1000L);
        Task task = new Task("task1", current, new Date(System.currentTimeMillis() + oneDayMillis), intervalSeconds);
        task.setActive(true);
        //act
        Date result = task.nextTimeAfter(new Date(System.currentTimeMillis() + intervalSeconds * 1000L));
        //assert
        assertEquals(next, result);
    }
}