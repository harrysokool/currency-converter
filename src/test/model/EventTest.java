package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Event class
 */
public class EventTest {
    private Event e;
    private Date d;

    //NOTE: these tests might fail if time at which line (2) below is executed
    //is different from time that line (1) is executed.  Lines (1) and (2) must
    //run in same millisecond for this test to make sense and pass.

    @BeforeEach
    public void runBefore() {
        e = new Event("Added currency: CAD");   // (1)
        d = Calendar.getInstance().getTime();   // (2)
    }

    @Test
    public void testEvent() {
        assertEquals("Added currency: CAD", e.getDescription());
        assertEquals(d, e.getDate());
    }

    @Test
    public void testToString() {
        assertEquals(d.toString() + "\n" + "Added currency: CAD", e.toString());
    }

    @Test
    public void testEquals() {
        Event e1 = null;
        String e2 = "";
        assertFalse(e.equals(e1));
        assertFalse(e.equals(e2));
    }

    @Test
    public void testHashCode() {
        int i = e.hashCode();
        assertEquals(i, e.hashCode());
    }
}
