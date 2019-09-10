package duke.statistics;

import duke.tasks.FormattedDateTime;

import java.time.LocalDate;

/**
 * Represents the task statistics of a single day. This class is used in {@code WeeklyStatistics} class to store
 * statistics of a particular day.
 */
public class DailyStatistics {
    private final FormattedDateTime dateAndTime;
    private int numOfCompletedTasks;
    private int numOfIncompleteTasks;
    private long missedDeadlines;

    /**
     * Instantiates a {@code DailyStatistics} object.
     * @param dateAndTime the date of the statistics that is being stored
     * @param numOfCompletedTasks number of tasks that has been completed
     * @param numOfIncompleteTasks number of tasks that has not been completed
     */
    public DailyStatistics(FormattedDateTime dateAndTime, int numOfCompletedTasks, int numOfIncompleteTasks,
                           long missedDeadlines) {
        this.dateAndTime = dateAndTime;
        this.numOfCompletedTasks = numOfCompletedTasks;
        this.numOfIncompleteTasks = numOfIncompleteTasks;
        this.missedDeadlines = missedDeadlines;
    }

    /**
     * Increases the number of completed tasks by 1.
     */
    void increaseCompletedTasks() {
        numOfCompletedTasks++;
    }

    /**
     * Increases the number of incomplete tasks by 1.
     */
    void increaseIncompleteTasks() {
        numOfIncompleteTasks++;
    }

    /**
     * Decreases the number of incomplete tasks by 1.
     */
    void decreaseIncompleteTasks() {
        numOfIncompleteTasks--;
    }

    /**
     * Returns the date of the statistics that is being stored.
     * @return a {@code LocalDate} object representing the date of the statistics
     */
    LocalDate getDate() {
        return this.dateAndTime.getDate();
    }

    void updateStats(int completedTasks, int incompleteTasks, long missedDeadlines) {
        this.numOfCompletedTasks = completedTasks;
        this.numOfIncompleteTasks = incompleteTasks;
        this.missedDeadlines = missedDeadlines;
    }

    private String getDateAsString() {
        return this.dateAndTime.convertDateToString();
    }

    @Override
    public String toString() {
        return getDateAsString() + "\n" + "You have completed " + numOfCompletedTasks + " tasks on this day.\n"
            + "You have " + numOfIncompleteTasks + " incomplete tasks on this day.\n"
            + "You have missed " + missedDeadlines + " deadlines.\n";
    }

    String convertToText() {
        return dateAndTime.toString() + " ~ " + numOfCompletedTasks + " ~ " + numOfIncompleteTasks
            + " ~ " + missedDeadlines;
    }
}
