package tasks.services;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tasks.model.ArrayTaskList;
import tasks.model.Task;
import tasks.model.TasksOperations;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class TasksService {
    private ArrayTaskList tasks;

    /**
     * Constructs a new TasksService with the provided ArrayTaskList.
     *
     * @param tasks The ArrayTaskList to be managed by this service.
     */
    public TasksService(ArrayTaskList tasks){
        this.tasks = tasks;
    }

    /**
     * Retrieves an observable list of tasks.
     *
     * @return An observable list of tasks.
     */
    public ObservableList<Task> getObservableList(){
        return FXCollections.observableArrayList(tasks.getAll());
    }

    public List<Task> getAllTasks() {
        return tasks.getAll();
    }

    /**
     * Converts the repeat interval of a task into hours and minutes format.
     *
     * @param task The task for which the repeat interval needs to be converted.
     * @return A string representing the repeat interval in "hh:MM" format.
     * @throws IllegalArgumentException If the input task is null.
     */
    public String getIntervalInHours(Task task){
        int seconds = task.getRepeatInterval();
        int minutes = seconds / DateService.SECONDS_IN_MINUTE;
        int hours = minutes / DateService.MINUTES_IN_HOUR;
        minutes = minutes % DateService.MINUTES_IN_HOUR;
        return formTimeUnit(hours) + ":" + formTimeUnit(minutes);//hh:MM
    }

    /**
     * Formats a time unit (hours or minutes) to ensure a consistent format with leading zeros if necessary.
     *
     * @param timeUnit The time unit value to be formatted.
     * @return A string representing the formatted time unit.
     */
    public String formTimeUnit(int timeUnit){
        StringBuilder sb = new StringBuilder();
        if (timeUnit < 10) sb.append("0");
        if (timeUnit == 0) sb.append("0");
        else {
            sb.append(timeUnit);
        }
        return sb.toString();
    }

    /**
     * Parses a time string in "hh:MM" format to seconds.
     *
     * @param stringTime The time string to be parsed.
     * @return The time in seconds.
     * @throws NumberFormatException If the input string is not in the expected format.
     */
    public int parseFromStringToSeconds(String stringTime){//hh:MM
        String[] units = stringTime.split(":");
        int hours = Integer.parseInt(units[0]);
        int minutes = Integer.parseInt(units[1]);
        int result = (hours * DateService.MINUTES_IN_HOUR + minutes) * DateService.SECONDS_IN_MINUTE;
        return result;
    }

    /**
     * Filters tasks based on the specified start and end dates.
     *
     * @param start The start date for filtering tasks.
     * @param end The end date for filtering tasks.
     * @return An iterable collection of tasks that fall within the specified date range.
     */
    public Iterable<Task> filterTasks(Date start, Date end){
        TasksOperations tasksOps = new TasksOperations(getObservableList());
        Iterable<Task> filtered = tasksOps.incoming(start,end);
        //Iterable<Task> filtered = tasks.incoming(start, end);

        return filtered;
    }
}
