package duke.storage;

import duke.exception.InvalidTimeAndDateException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.FormattedDate;
import duke.tasks.Task;
import duke.tasks.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents the storage system of Duke. A {@code Storage} object helps to load the previously stored text file in
 * duke.model.Duke and saves the updates that have been made.
 */
public class Storage {
    private File tasktext;
    private String filePath;

    /**
     * Instantiates a {@code Storage} object. A new directory and file is created if the file has yet to be created.
     *
     * @param filePath the path of the file
     */
    public Storage(String filePath) {
        try {
            this.tasktext = new File(filePath);
            this.filePath = filePath;
            if (!tasktext.exists()) {
                tasktext.getParentFile().mkdir();
                tasktext.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Invalid file path! Unable to find Duke's memory.");
        }
    }

    /**
     * Initialises the list of items that have been stored in the text file of the {@code Storage} instance.
     *
     * @return an {@code ArrayList} of {@code Task} objects that are stored in text format in the file.
     */
    public ArrayList<Task> load() {
        ArrayList<Task> initialisedList = new ArrayList<>();
        try {
            Scanner scanItems = new Scanner(this.tasktext);
            while (scanItems.hasNext()) {
                initialisedList.add(processLine(scanItems.nextLine()));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Unable to find Duke's memory. Please provide the correct file!");
        } catch (ParseException e) {
            System.out.println("Wrong text formatting in the file! Please format the file properly!");
        } catch (InvalidTimeAndDateException e) {
            System.out.println("Are you sure you are not travelling to the past? Please check again!");
        }
        return initialisedList;
    }

    private Task processLine(String line) throws ParseException, InvalidTimeAndDateException {
        String[] tokens = line.split(" ~ ");
        boolean isDone = Boolean.parseBoolean(tokens[1]);
        if (tokens[0].equals("T")) {
            return new ToDo(tokens[2], isDone);
        } else if (tokens[0].equals("E")) {
            String[] startAndEnd = tokens[3].split(" - ");
            FormattedDate start = new FormattedDate(startAndEnd[0]);
            FormattedDate end = new FormattedDate(startAndEnd[1]);
            return new Event(tokens[2], isDone, start, end);
        } else {
            FormattedDate by = new FormattedDate(tokens[3]);
            return new Deadline(tokens[2], isDone, by);
        }
    }

    /**
     * Saves the {@code Task} objects that are stored in the list in text format.
     *
     * @param list an {@code ArrayList} of {@code Task} objects
     */
    public void save(ArrayList<Task> list) {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (Task task : list) {
                fw.write(task.convertToText() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Unable to find Duke's memory location!");
        }
    }
}
