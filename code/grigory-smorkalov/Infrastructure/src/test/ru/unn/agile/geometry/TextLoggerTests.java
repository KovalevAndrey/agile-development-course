package ru.unn.agile.geometry;

import ru.unn.agile.geometry.TextLogger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

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

    private List<String> readLogFile() {
        BufferedReader reader;
        ArrayList<String> log = new ArrayList<String>();
        try {
            reader = new BufferedReader(new FileReader("./TextLoggerTest.log"));
            String line = null;
            while ((line = reader.readLine()) != null) {
                log.add(line);
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return log;
    }

    @Test
    public void canLogMessage() {
        String message = "message";
        logger.message(message);
        assertEquals(readLogFile().get(0), message);
    }

}
