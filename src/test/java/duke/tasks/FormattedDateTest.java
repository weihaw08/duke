package duke.tasks;

import duke.exception.InvalidTimeAndDateException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FormattedDateTest {

    @ParameterizedTest
    @ValueSource(strings = {"300", "i", "20/12/2997 8pm"})
    void testFormattedDateConstructor_invalidDateFormat_exceptionThrown(String s) throws InvalidTimeAndDateException {
        try {
            new FormattedDate(s);
        } catch (ParseException e) {
            assertEquals("java.text.ParseException: Unparseable date: \"" + s + "\"", e.toString());
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"16/06/1997 2000", "17/09/2019 2500"})
    void testFormattedDateConstructor_invalidTimeAndDate_exceptionThrown(String s) throws ParseException {
        try {
            new FormattedDate(s);
        } catch (InvalidTimeAndDateException e) {
            assertEquals("☹ OOPS!!! You are breaking the space-time continuum!", e.toString());
        }
    }

    @Test
    void testStringConversion() throws InvalidTimeAndDateException, ParseException {
        FormattedDate date = new FormattedDate("23/07/2020 2000");
        assertEquals("23/07/2020 2000", date.toString());
    }

}
