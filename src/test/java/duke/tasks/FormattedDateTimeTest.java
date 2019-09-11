package duke.tasks;

import org.junit.jupiter.api.Test;

import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FormattedDateTimeTest {

    @Test
    void testFormattedDateTimeConstructor_invalidDateFormat_exceptionThrown() {
        try {
            new FormattedDateTime("20/12/2997 8pm");
        } catch (DateTimeParseException e) {
            assertEquals("Text '20/12/2997 8pm' could not be parsed at index 11", e.getMessage());
        }
    }

    @Test
    void testFormattedDateTimeConstructor_wrongInput_exceptionThrown() {
        try {
            new FormattedDateTime("300");
        } catch (DateTimeParseException e) {
            assertEquals("Text '300' could not be parsed at index 2", e.getMessage());
        }
    }

    @Test
    void testStringConversion() {
        FormattedDateTime date = new FormattedDateTime("23/07/2020 2000");
        assertEquals("23/07/2020 2000", date.toString());
    }

}
