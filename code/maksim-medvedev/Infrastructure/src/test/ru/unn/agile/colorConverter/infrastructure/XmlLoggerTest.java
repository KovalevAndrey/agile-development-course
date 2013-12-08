package ru.unn.agile.colorConverter.infrastructure;


import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.*;
import java.nio.file.Files;
import java.nio.file.Paths;
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
    public void canWriteLogMessage() throws IOException {
        String testMessage = "Test log message";
        logger.log(testMessage);

        String logContent = readAllContent(filename, StandardCharsets.UTF_8);

        assert(logContent.contains(testMessage));
    }

    @Test
    public void canWriteSeveralMessages() throws IOException {
        String[] messages = new String[] { "msg 1", "msg 2" };

        logger.log(messages[0]);
        logger.log(messages[1]);

        String logContent = readAllContent(filename, StandardCharsets.UTF_8);

        assert(logContent.contains(messages[0]));
        assert(logContent.contains(messages[1]));
    }

    @Test
    public void logMessageContentsTimeStamp() throws IOException {
        logger.log("test message");

        String logContent = readAllContent(filename, StandardCharsets.UTF_8);

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

    private String readAllContent(String path, Charset encoding)
            throws IOException
    {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return encoding.decode(ByteBuffer.wrap(encoded)).toString();
    }
}
