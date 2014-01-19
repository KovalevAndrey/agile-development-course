package ru.unn.agile.geometry;

import ru.unn.agile.geometry.TextLogger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TextLoggerTests {
    private TextLogger logger;

    @Before
    public void setUp() {
        logger = new TextLogger("./TextLoggerTest.log");
    }

    @Test
    public void canCreateLogger() {
        assertNotEquals(null, logger);
    }

    @Test
    public void whenFileNameIsEmptyCatchException() {
        try {
            new TextLogger("");
            fail("Exception was expected");
        }
        catch(IllegalArgumentException ex) {
            assertTrue(true);
        }
        catch(Exception ex) {
            fail("Invalid exception type");
        }
    }

}
