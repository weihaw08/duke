package duke.statistics;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Stores the weekly statistics of the user. This class helps to store daily statistics that are up to 6 days old. Any
 * daily statistics that are more than 6 days old will be removed.
 */
public class WeeklyStatistics {
    private ArrayList<DailyStatistics> statisticsList;
    private static final int maxSize = 7;

    /**
     * Instantiates a {@code WeeklyStatistics} object.
     */
    public WeeklyStatistics() {
        statisticsList = new ArrayList<>();
    }

    /**
     * Adds the latest {@code DailyStatistics} object into the list of statistics. If the statistics list is full, the
     * oldest {@code DailyStatistics} object will be removed.
     * @param stats the latest {@code DailyStatistics} object to be added
     */
    public void addNewDailyStatistics(DailyStatistics stats) {
        if (statisticsList.size() == maxSize) {
            statisticsList.remove(0);
        }
        statisticsList.add(stats);
    }

    /**
     * Retrieves the latest date that is present inside the statistics list.
     * @return a {@code FormattedDate} object representing the latest date inside the statistics list
     */
    public LocalDate getLatestDate() {
        int size = statisticsList.size();
        return statisticsList.get(size - 1).getDate();
    }

    /**
     * Increases the number of incomplete tasks in the latest {@code DailyStatistics} object by 1.
     */
    public void addLatestIncompleteTask() {
        int size = statisticsList.size();
        statisticsList.get(size - 1).increaseIncompleteTasks();
    }

    /**
     * Increases the number of completed tasks in the latest {@code DailyStatistics} object by 1 and decreases
     * the number of incomplete tasks in the latest {@code DailyStatistics} object by 1.
     */
    public void addLatestCompletedTask() {
        int size = statisticsList.size();
        DailyStatistics latestStats = statisticsList.get(size - 1);
        latestStats.increaseCompletedTasks();
        latestStats.decreaseIncompleteTasks();
    }

    /**
     * Decreases the number of incomplete tasks in the latest {@code DailyStatistics} object by 1.
     */
    public void reduceLatestIncompleteTask() {
        int size = statisticsList.size();
        statisticsList.get(size - 1).decreaseIncompleteTasks();
    }

    /**
     * Consolidates all of the daily statistics.
     */
    private StringBuilder computeDailyStatistics() {
        StringBuilder res = new StringBuilder();
        for (DailyStatistics stats : statisticsList) {
            res.append(stats.toString()).append("\n");
        }
        return res;
    }

    /**
     * Computes and consolidates daily and weekly statistics into a single string.
     * @return a string representing all of the daily and weekly statistics
     */
    public String computeAllStatistics() {
        StringBuilder dailyStats = computeDailyStatistics();
        return dailyStats.toString();
    }

    /**
     * Updates the different statistics in the latest {@code DailyStatistics} object.
     * @param completedTasks the number of completed tasks
     * @param incompleteTasks the number of incomplete tasks
     * @param missedDeadlines the number of deadlines missed
     */
    public void updateLatestStats(int completedTasks, int incompleteTasks, long missedDeadlines) {
        int size = statisticsList.size();
        statisticsList.get(size - 1).updateStats(completedTasks, incompleteTasks, missedDeadlines);
    }

    /**
     * Converts each of the {@code DailyStatistics} object in the list into text form for storage.
     * @return a string representing the daily statistics in the list in text form
     */
    public String convertToText() {
        StringBuilder res = new StringBuilder();
        for (DailyStatistics stats : statisticsList) {
            res.append(stats.convertToText()).append("\n");
        }
        return res.toString();
    }

    /**
     * Checks if the statistics list is empty.
     * @return true if and only if the statistics list is empty.
     */
    public boolean isEmpty() {
        return statisticsList.isEmpty();
    }
}
