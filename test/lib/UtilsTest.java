package lib;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UtilsTest {

    @Test
    void testParseDurationToDays() {
        // valid inputs
        assertEquals(5, Utils.parseDurationToDays("5d"));
        assertEquals(14, Utils.parseDurationToDays("2w"));
        assertEquals(90, Utils.parseDurationToDays("3m"));

        // invalid input -> should throw IllegalArgumentException
        assertThrows(IllegalArgumentException.class,
                () -> Utils.parseDurationToDays("abc"));
    }
}
