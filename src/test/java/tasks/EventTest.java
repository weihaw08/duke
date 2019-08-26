package tasks;

import duke.exception.InvalidTimeAndDateException;
import duke.tasks.Event;
import org.junit.jupiter.api.Test;
import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EventTest {

    @Test
    void testStringConversion() throws InvalidTimeAndDateException, ParseException {
        // Testing string conversion for constructor 1
        FormattedDateStub date = new FormattedDateStub("s");
        Event e1 = new Event("project meeting", date, date);
        assertEquals("[E][✗] project meeting (at: 20/10/3999 2345 - 20/10/3999 2345)", e1.toString());

        // Testing string conversion for constructor 2
        Event e2 = new Event("sleep", true, date, date);
        assertEquals("[E][✓] sleep (at: 20/10/3999 2345 - 20/10/3999 2345)", e2.toString());
    }

    @Test
    void testIsDoneFunction() throws InvalidTimeAndDateException, ParseException {
        FormattedDateStub date = new FormattedDateStub("s");
        Event e1 = new Event("project meeting", date, date);
        e1.markAsDone();
        assertEquals("[E][✓] project meeting (at: 20/10/3999 2345 - 20/10/3999 2345)", e1.toString());
    }

    @Test
    void testTextConversion() throws InvalidTimeAndDateException, ParseException {
        FormattedDateStub date = new FormattedDateStub("s");
        Event e1 = new Event("sleep", date, date);
        Event e2 = new Event("sleep", true, date, date);
        assertEquals("E ~ false ~ sleep ~ 20/10/3999 2345 - 20/10/3999 2345", e1.convertToText());
        assertEquals("E ~ true ~ sleep ~ 20/10/3999 2345 - 20/10/3999 2345", e2.convertToText());
    }
}
