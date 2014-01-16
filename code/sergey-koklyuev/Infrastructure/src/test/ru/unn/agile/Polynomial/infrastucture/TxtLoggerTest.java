package ru.unn.agile.Polynomial.infrastucture;

import org.junit.Before;
import org.junit.Test;
import ru.unn.agile.Polynomial.viewmodel.LoggingLevel;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class TxtLoggerTest {

    private TxtLogger logger;
    private String filename = "log.txt";

    @Before
    public void setUp() {
        logger = new TxtLogger(filename);
    }

    @Test
    public void canCreateTxtLogger() {
        logger = new TxtLogger(filename);
        assertTrue(logger != null);
    }

    @Test
    public void canSetAndGetLevel() {
        logger.setLevel(LoggingLevel.DEBUG);
        assertEquals(LoggingLevel.DEBUG, logger.getLevel());
        logger.setLevel(LoggingLevel.RELEASE);
        assertEquals(LoggingLevel.RELEASE, logger.getLevel());
    }

    @Test
    public void canCreateLogFileOnDisk() {
        try {
            new BufferedReader(new FileReader(filename));
        } catch (FileNotFoundException e) {
            fail("File " + filename + " not found");
        }
    }

    @Test
    public void canWriteLogMessage() {
        logger.log(LoggingLevel.DEBUG, "Hello!");

        String message = readLines().get(0);

        assertTrue(message.equals("Hello!"));
    }

    @Test
    public void canWriteSeveralMessages() {
        logger.log(LoggingLevel.DEBUG, "Hello!");
        logger.log(LoggingLevel.DEBUG, "Hi!");

        List<String> messages = readLines();
        assertTrue(messages.get(0).equals("Hello!"));
        assertTrue(messages.get(1).equals("Hi!"));
    }

    @Test
    public void canGetLog() {
        logger.log(LoggingLevel.DEBUG, "Hello!");
        logger.log(LoggingLevel.DEBUG, "Hi!");

        List<String> messages = logger.getLog();
        assertTrue(messages.get(0).equals("Hello!"));
        assertTrue(messages.get(1).equals("Hi!"));
    }

    @Test
    public void canGetLastMessage() {
        logger.log(LoggingLevel.DEBUG, "Hello!");

        String message = logger.getLastMessage();
        assertTrue(message.equals("Hello!"));
    }

    private List<String> readLines() {
        BufferedReader reader;
        ArrayList<String> log = new ArrayList<String>();
        try {
            reader = new BufferedReader(new FileReader(filename));
            String line = reader.readLine();

            while (line != null) {
                log.add(line);
                line = reader.readLine();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return log;
    }

}
