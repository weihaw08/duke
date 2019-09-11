package duke.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeadlineTest {

    @Test
    void testStringConversion() {
        // Testing string conversion for constructor 1
        FormattedDateStub date = new FormattedDateStub("s");
        Deadline d1 = new Deadline("project meeting", date);
        assertEquals("[D][Not Done] project meeting (by: 20/10/3999 2345)", d1.toString());

        // Testing string conversion for constructor 2
        Deadline d2 = new Deadline("sleep", true, date);
        assertEquals("[D][Done] sleep (by: 20/10/3999 2345)", d2.toString());
    }

    @Test
    void testIsDoneFunction() {
        FormattedDateStub date = new FormattedDateStub("s");
        Deadline d1 = new Deadline("project meeting", date);
        d1.markAsDone();
        assertEquals("[D][Done] project meeting (by: 20/10/3999 2345)", d1.toString());
    }

    @Test
    void testTextConversion() {
        FormattedDateStub date = new FormattedDateStub("s");
        Deadline d1 = new Deadline("sleep", date);
        Deadline d2 = new Deadline("sleep", true, date);
        assertEquals("D ~ false ~ sleep ~ 20/10/3999 2345", d1.convertToText());
        assertEquals("D ~ true ~ sleep ~ 20/10/3999 2345", d2.convertToText());
    }
}
