package duke.storage;

import duke.model.TaskList;
import duke.statistics.DailyStatistics;
import duke.statistics.WeeklyStatistics;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.FormattedDateTime;
import duke.tasks.Task;
import duke.tasks.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import static java.time.temporal.ChronoUnit.DAYS;

/**
 * Represents the storage system of Duke. A {@code Storage} object helps to load the previously stored text file in
 * Duke and saves the updates that have been made.
 */
public class Storage {
    private File taskText;
    private File statisticsText;
    private String filePath;
    private String statisticsPath;

    /**
     * Helps to initialise any files that may not be present.
     */
    private void initialiseFile(File file) {
        try {
            if (!file.exists()) {
                taskText.getParentFile().mkdir();
                taskText.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Invalid file path! Please enter a correct file path!");
        }
    }

    /**
     * Instantiates a {@code Storage} object. A new directory and file is created if the file has yet to be created.
     *
     * @param filePath the path of the file
     */
    public Storage(String filePath, String statisticsPath) {
        this.taskText = new File(filePath);
        this.statisticsText = new File(statisticsPath);
        this.filePath = filePath;
        this.statisticsPath = statisticsPath;
        initialiseFile(taskText);
        initialiseFile(statisticsText);
    }

    /**
     * Initialises the list of items that have been stored in the text file of the {@code Storage} instance.
     *
     * @return an {@code ArrayList} of {@code Task} objects that are stored in text format in the file.
     */
    public ArrayList<Task> loadTasks() {
        ArrayList<Task> initialisedList = new ArrayList<>();
        try {
            Scanner scanItems = new Scanner(this.taskText);
            while (scanItems.hasNext()) {
                initialisedList.add(processTaskLine(scanItems.nextLine()));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Unable to find Duke's memory. Please provide the correct file!");
        }

        return initialisedList;
    }

    /**
     * Helps to standardise the dates that are being used in the {@code DailyStatistics} object. The time of each
     * date is standardised to 2359.
     */
    private FormattedDateTime createStatsDate(LocalDate date) {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmm");
        LocalTime time = LocalTime.parse("2359", timeFormatter);
        LocalDateTime dateAndTime = LocalDateTime.of(date, time);
        return new FormattedDateTime(dateAndTime);
    }

    /**
     * Helps to initialise the very first statistics in the {@code WeeklyStatistics} object. This occurs only when the
     * statistics list is empty.
     */
    private void initialiseFirstStats(TaskList taskList, WeeklyStatistics statsList) {
        FormattedDateTime currentDate = createStatsDate(LocalDate.now());
        int completedTasks = taskList.getNumOfCompletedTasks();
        int incompleteTasks = taskList.size() - completedTasks;
        DailyStatistics newStats = new DailyStatistics(currentDate, completedTasks, incompleteTasks, 0);
        statsList.addNewDailyStatistics(newStats);
    }

    /**
     * Helps to add in all of the missing statistics from the text file into the statistics list. Only dates that
     * are up to 6 days old before the current date will be considered.
     */
    private void addMissingStats(TaskList taskList, WeeklyStatistics statsList) {
        LocalDate currentDate = LocalDate.now();
        int completedTasks = taskList.getNumOfCompletedTasks();
        int incompleteTasks = taskList.size() - completedTasks;
        long missedDeadlines = taskList.getNumOfMissedDeadlines();
        LocalDate latestDate = statsList.getLatestDate();
        if (!latestDate.isEqual(currentDate)) {
            long diffInDays = DAYS.between(latestDate, currentDate);
            long minDiff = Math.min(diffInDays - 1, 6);
            for (long i = minDiff; i >= 0; i--) {
                LocalDate date = currentDate.minusDays(i);
                FormattedDateTime dateTime = createStatsDate(date);
                DailyStatistics statsToAdd = new DailyStatistics(dateTime, completedTasks, incompleteTasks,
                    missedDeadlines);
                statsList.addNewDailyStatistics(statsToAdd);
            }
        }
        statsList.updateLatestStats(completedTasks, incompleteTasks, missedDeadlines);
    }


    /**
     * Helps to load all of the statistics that are stored inside a text file into a {@code WeeklyStatistics} object.
     * @param taskList the {@code TaskList} object in Duke
     * @return a {@code WeeklyStatistics} object that contains the daily statistics of the past 7 days
     */
    public WeeklyStatistics loadStats(TaskList taskList) {
        WeeklyStatistics statsList = new WeeklyStatistics();
        try {
            Scanner scanItems = new Scanner(this.statisticsText);
            while (scanItems.hasNext()) {
                statsList.addNewDailyStatistics(processStatsLine(scanItems.nextLine()));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Unable to find Duke's memory. Please provide the correct file!");
        }

        if (statsList.isEmpty()) {
            initialiseFirstStats(taskList, statsList);
        } else {
            addMissingStats(taskList, statsList);
        }
        return statsList;
    }

    /**
     * Converts each line in the statistics text file into a {@code DailyStatistics} object.
     */
    private DailyStatistics processStatsLine(String line) {
        String[] tokens = line.split(" ~ ");
        FormattedDateTime date = new FormattedDateTime(tokens[0]);
        int completedTasks = Integer.parseInt(tokens[1]);
        int incompleteTasks = Integer.parseInt(tokens[2]);
        long missedDeadlines = Long.parseLong(tokens[3]);
        return new DailyStatistics(date, completedTasks, incompleteTasks, missedDeadlines);
    }

    /**
     * Converts each line in the task text file into a {@code Task} object.
     */
    private Task processTaskLine(String line) {
        String[] tokens = line.split(" ~ ");
        boolean isDone = Boolean.parseBoolean(tokens[1]);
        if (tokens[0].equals("T")) {
            return new ToDo(tokens[2], isDone);
        } else if (tokens[0].equals("E")) {
            String[] startAndEnd = tokens[3].split(" - ");
            FormattedDateTime start = new FormattedDateTime(startAndEnd[0]);
            FormattedDateTime end = new FormattedDateTime(startAndEnd[1]);
            return new Event(tokens[2], isDone, start, end);
        } else {
            FormattedDateTime by = new FormattedDateTime(tokens[3]);
            return new Deadline(tokens[2], isDone, by);
        }
    }

    /**
     * Saves the {@code Task} objects that are stored in the list in text format.
     *
     * @param list an {@code ArrayList} of {@code Task} objects
     */
    public void saveTasks(TaskList list) {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (int i = 1; i <= list.size(); i++) {
                Task task = list.retrieveTask(i);
                fw.write(task.convertToText() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Unable to find Duke's memory location!");
        }
    }

    /**
     * Saves the contents in a {@code WeeklyStatistics} object into a text file.
     * @param stats the {@code WeeklyStatistics} object in Duke
     */
    public void saveStatistics(WeeklyStatistics stats) {
        try {
            FileWriter fw = new FileWriter(statisticsPath);
            fw.write(stats.convertToText());
            fw.close();
        } catch (IOException e) {
            System.out.println("Unable to find Duke's memory location!");
        }
    }
}
