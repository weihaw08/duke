package duke.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ToDoTest {

    @Test
    void testStringConversion() {
        // Testing string conversion for constructor 1
        assertEquals("[T][✗] sleep", new ToDo("sleep").toString());

        // Testing string conversion for constructor 2
        assertEquals("[T][✓] talk", new ToDo("talk", true).toString());
    }

    @Test
    void testIsDoneFunction() {
        ToDo t = new ToDo("sleep");
        t.markAsDone();
        assertEquals("[T][✓] sleep", t.toString());
    }

    @Test
    void testTextConversion() {
        assertEquals("T ~ false ~ sleep", new ToDo("sleep").convertToText());
        assertEquals("T ~ true ~ talk", new ToDo("talk", true).convertToText());
    }
}
