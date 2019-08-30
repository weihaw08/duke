package duke.tasks;

import duke.exception.InvalidTimeAndDateException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.util.Date;

/**
 * Helps to determine if a date is valid and represents a date in the standardised format "dd/mm/yyyy hhmm".
 */
public class FormattedDate implements Comparable<FormattedDate> {
    private Date date;
    private static final SimpleDateFormat FORMAT = new SimpleDateFormat("dd/MM/yyyy HHmm");

    /**
     * Instantiates a {@code FormattedDate} object.
     *
     * @param s a string that could possibly represent a date in the format "dd/mm/yyyy hhmm"
     * @throws ParseException              if the string is not a date or is not in the "dd/mm/yyyy hhmm" format
     * @throws InvalidTimeAndDateException if the date and time is not in an official time system
     */
    public FormattedDate(String s) throws ParseException, InvalidTimeAndDateException {
        this.date = FORMAT.parse(s);
        String[] tokens = s.split(" ");
        Date currentDate = new Date();
        if (!isValidDate(tokens[0]) || !isValidTime(tokens[1])) {
            throw new InvalidTimeAndDateException(s);
        }
    }

    private boolean isValidDate(String date) {
        String[] tokens = date.split("/");
        int day = Integer.parseInt(tokens[0]);
        int month = Integer.parseInt(tokens[1]);
        int year = Integer.parseInt(tokens[2]);
        if (month < 0 || month > 12) {
            return false;
        } else {
            YearMonth findDays = YearMonth.of(year, month);
            return day <= findDays.lengthOfMonth() && day >= 1;
        }
    }

    private boolean isValidTime(String time) {
        int value = Integer.parseInt(time);
        return value >= 0 && value <= 2359;
    }

    @Override
    public String toString() {
        return FORMAT.format(this.date);
    }

    @Override
    public int compareTo(FormattedDate anotherDate) {
        return this.date.compareTo(anotherDate.date);
    }
}
