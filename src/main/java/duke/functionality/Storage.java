package duke.functionality;

import duke.exception.InvalidTimeAndDateException;
import duke.tasks.FormattedDate;
import duke.tasks.Task;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.ToDo;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private File tasktext;
    private String filePath;

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
        boolean isDone = Boolean.valueOf(tokens[1]);
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

    public void save(ArrayList<Task> list) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (Task task : list) {
            fw.write(task.convertToText() + "\n");
        }
        fw.close();
    }
}