package duke.tasks;

import duke.exception.InvalidTimeAndDateException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.util.Date;

public class FormattedDate {
    private Date date;
    private static final SimpleDateFormat FORMAT = new SimpleDateFormat("dd/MM/yyyy HHmm");

    /**
     * Instantiates a {@code FormattedDate} object.
     * @param s a string that possibly represents a valid date
     * @throws ParseException if the date is not of the "dd/mm/yyyy hhmm" format
     * @throws InvalidTimeAndDateException if the date or time is incorrect
     */
    public FormattedDate(String s) throws ParseException, InvalidTimeAndDateException {
        this.date = FORMAT.parse(s);
        String[] tokens = s.split(" ");
        Date currentDate = new Date();
        if (!isValidDate(tokens[0]) || !isValidTime(tokens[1]) || this.date.before(currentDate)) {
            throw new InvalidTimeAndDateException();
        }
    }

    private boolean isValidDate(String date) {
        String[] tokens = date.split("/");
        int day = Integer.parseInt(tokens[0]);
        int month = Integer.parseInt(tokens[1]);
        int year = Integer.parseInt(tokens[2]);
        YearMonth findDays = YearMonth.of(year, month);
        return month >= 1 && month <= 12 && day <= findDays.lengthOfMonth() && day >= 1;
    }

    private boolean isValidTime(String time) {
        int value = Integer.parseInt(time);
        return value >= 0 && value <= 2359;
    }

    @Override
    public String toString() {
        return FORMAT.format(this.date);
    }
}
