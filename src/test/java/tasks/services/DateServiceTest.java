package tasks.services;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import tasks.model.ArrayTaskList;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class DateServiceTest {

    private static DateService service;
    private static Date noTimeDate;
    private static String time;
    private static String exceptionMsg;
    private static Date result;

    static Date getDateWithoutTimeUsingCalendar(int hour, int min) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, min);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    @BeforeAll
    static void beforeAll() {
        service = new DateService(new TasksService(new ArrayTaskList()));
        exceptionMsg = "time unit exceeds bounds";
    }

    @BeforeEach
    void setUp() {
        noTimeDate = getDateWithoutTimeUsingCalendar(0, 0);
        result = null;
    }

    @AfterEach
    void tearDown() {
        noTimeDate = null;
        result = null;
    }

    @AfterAll
    static void afterAll() {
        noTimeDate = null;
        result = null;
        service = null;
        exceptionMsg = null;
    }


    @Test
    @DisplayName("Valid Date and Time Parsing")
    @Tag("ECP testing")
    void givenValidDateAndTime_whenParsingDate_thenShouldReturnDate() {
        // arrange
        time = "12:43";

        //act
        try {
            result = service.getDateMergedWithTime(time, noTimeDate);
        }
        //assert
        catch (IllegalArgumentException e) {
            fail(e.getMessage());
        }

        Date expected = getDateWithoutTimeUsingCalendar(12, 43);
        assertNotNull(result);
        assertEquals(expected, result);
    }

    @Test
    @Tag("ECP testing")
    @Timeout(5)
    void given23Hour59Minutes_whenParsingDate_thenShouldReturnDate() {
        // arrange
        time = "23:59";

        //act
        try {
            result = service.getDateMergedWithTime(time, noTimeDate);
        }
        //assert
        catch (IllegalArgumentException e) {
            fail(e.getMessage());
        }

        Date expected = getDateWithoutTimeUsingCalendar(23, 59);
        assertNotNull(result);
        assertEquals(expected, result);
    }

    @Test
    @Tag("ECP testing")
    void given00Hour00Minutes_whenParsingDate_thenShouldReturnDate() {
        // arrange
        time = "00:00";

        //act
        try {
            result = service.getDateMergedWithTime(time, noTimeDate);
        }
        //assert
        catch (IllegalArgumentException e) {
            fail(e.getMessage());
        }

        Date expected = getDateWithoutTimeUsingCalendar(00, 00);
        assertNotNull(result);
        assertEquals(expected, result);
    }

    @Test
    @Tag("BVA testing")
    void givenNegativeHours_whenParsingDate_thenShouldTrowException() {
        // arrange
        time = "-01:12";

        //act
        try {
            result = service.getDateMergedWithTime(time, noTimeDate);
        }
        //assert
        catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), exceptionMsg);
        }
        assertNull(result);
    }

    @Test
    @Tag("BVA testing")
    void givenGreaterThan24Hours_whenParsingDate_thenShouldTrowException() {
        // arrange
        time = "24:12";

        //act
        try {
            result = service.getDateMergedWithTime(time, noTimeDate);
        }
        //assert
        catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), exceptionMsg);
        }
        assertNull(result);
    }

    @Test
    @Tag("BVA testing")
    void givenNegativeMinutes_whenParsingDate_thenShouldTrowException() {
        // arrange
        time = "13:-01";

        //act
        try {
            result = service.getDateMergedWithTime(time, noTimeDate);
            assert(false);
        }
        //assert
        catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), exceptionMsg);
        }
        assertNull(result);
    }

    @Disabled
    @Test
    @Tag("BVA testing")
    @EnabledOnOs(OS.LINUX)
    void givenGreaterThan59Minutes_whenParsingDate_thenShouldTrowException() {
        // arrange
        time = "13:60";

        //act
        try {
            result = service.getDateMergedWithTime(time, noTimeDate);
        }
        //assert
        catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), exceptionMsg);
        }
        assertNull(result);
    }
}
