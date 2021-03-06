package duke.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EventTest {

    @Test
    void testStringConversion() {
        // Testing string conversion for constructor 1
        FormattedDateStub date = new FormattedDateStub("s");
        Event e1 = new Event("project meeting", date, date);
        assertEquals("[E][Not Done] project meeting (at: 20/10/3999 2345 - 20/10/3999 2345)", e1.toString());

        // Testing string conversion for constructor 2
        Event e2 = new Event("sleep", true, date, date);
        assertEquals("[E][Done] sleep (at: 20/10/3999 2345 - 20/10/3999 2345)", e2.toString());
    }

    @Test
    void testIsDoneFunction() {
        FormattedDateStub date = new FormattedDateStub("s");
        Event e1 = new Event("project meeting", date, date);
        e1.markAsDone();
        assertEquals("[E][Done] project meeting (at: 20/10/3999 2345 - 20/10/3999 2345)", e1.toString());
    }

    @Test
    void testTextConversion() {
        FormattedDateStub date = new FormattedDateStub("s");
        Event e1 = new Event("sleep", date, date);
        Event e2 = new Event("sleep", true, date, date);
        assertEquals("E ~ false ~ sleep ~ 20/10/3999 2345 - 20/10/3999 2345", e1.convertToText());
        assertEquals("E ~ true ~ sleep ~ 20/10/3999 2345 - 20/10/3999 2345", e2.convertToText());
    }
}
