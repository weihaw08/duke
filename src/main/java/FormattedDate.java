import java.util.Date;
import java.time.YearMonth;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class FormattedDate {
    private Date date;
    private static final SimpleDateFormat FORMAT = new SimpleDateFormat("dd/MM/yyyy HHmm");

    FormattedDate(String s) throws ParseException, InvalidTimeAndDateException {
        this.date = FORMAT.parse(s);
        String[] tokens = s.split(" ");
        Date currentDate = new Date();
        if (!isValidDate(tokens[0]) || !isValidTime(tokens[1]) || this.date.before(currentDate)) {
            throw new InvalidTimeAndDateException();
        }
    }

    private boolean isValidDate(String date) {
        String[] tokens = date.split("/");
        int day = Integer.valueOf(tokens[0]);
        int month = Integer.valueOf(tokens[1]);
        int year = Integer.valueOf(tokens[2]);
        YearMonth findDays = YearMonth.of(year, month);
        return month >= 1 && month <= 12 && day <= findDays.lengthOfMonth() && day >= 1;
    }

    private boolean isValidTime(String time) {
        int value = Integer.valueOf(time);
        return value >= 0 && value <= 2359;
    }

    @Override
    public String toString() {
        return FORMAT.format(this.date);
    }
}
