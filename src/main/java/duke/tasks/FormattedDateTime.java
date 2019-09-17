package duke.tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Helps to determine if a date is valid and represents a date in the standardised format "dd/MM/yyyy HHmm".
 */
public class FormattedDateTime {
    private LocalDateTime dateTime;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

    /**
     * Instantiates a {@code FormattedDate} object.
     *
     * @param s a string that could possibly represent a date in the format "dd/MM/yyyy HHmm"
     */
    public FormattedDateTime(String s) {
        this.dateTime = LocalDateTime.parse(s, formatter);
    }

    public FormattedDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public LocalDate getDate() {
        return this.dateTime.toLocalDate();
    }

    LocalDateTime getDateAndTime() {
        return this.dateTime;
    }

    public String convertDateToString() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return this.getDate().format(dateFormatter);
    }

    public boolean isAfter(FormattedDateTime anotherDateTime) {
        return this.dateTime.isAfter(anotherDateTime.dateTime);
    }

    @Override
    public String toString() {
        return this.dateTime.format(formatter);
    }

}
