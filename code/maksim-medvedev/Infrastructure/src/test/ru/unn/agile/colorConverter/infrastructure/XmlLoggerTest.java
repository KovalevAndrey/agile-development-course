package ru.unn.agile.colorConverter.infrastructure;


import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.*;

public class XmlLoggerTest {
    private static final String filename = "./LoggerTests.xml";
    XmlLogger logger = null;

    @Before
    public void setUp() throws Exception {
        logger = new XmlLogger(filename);
    }

    @Test
    public void canCreateLoggerWithFileName() {
        assertNotNull(logger);
    }

    @Test
    public void canCreateLogFileOnDisk() {
        try {
            new BufferedReader(new FileReader(filename));
        } catch (FileNotFoundException e) {
            fail("File " + filename + " wasn't found!");
        }
    }

    @Test
    public void canWriteLogMessage() {
        String testMessage = "Test log message";
        logger.log(testMessage);

        String logContent = readAllText();

        assert(logContent.contains(testMessage));
    }

    public void canWriteSeveralMessages() {
        String[] messages = new String[] { "msg 1", "msg 2", "msg 3" };

        for (int i = 0; i < messages.length; i++)
            logger.log(messages[i]);

        String logContent = readAllText();

        for (int i = 0; i < messages.length; i++)
            assert(logContent.contains(messages[i]));
    }

    public void logMessageContentsTimeStamp() {
        logger.log("test message");

        String logContent = readAllText();

        assert(logContent.contains("timestamp=\""));
    }

    @Test
    public void canGetContent() {
        logger.log("message 1");
        logger.log("message 2");

        List<String> messages = logger.getContent();

        assertEquals(messages.get(0), "message 1");
        assertEquals(messages.get(1), "message 2");
    }

    private String readAllText() {
        String content = "";
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(filename));
            String line = reader.readLine();

            while (line != null) {
                content = line + "\n";
                line = reader.readLine();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return content;
    }
}
