package ru.unn.agile.qsolver.infrastructure;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.List;

import static org.junit.Assert.*;

public class FileLoggerTest {
    private static final String fileName = "./FileLoggerTest.log";
    private FileLogger logger;

    @Before
    public void setUp() {
        logger = new FileLogger(fileName);
    }

    @After
    public void tearDown() {
        logger = null;
    }

    @Test
    public void loggerCanBeCreatedWithTheFileName() {
        assertNotNull(logger);
    }

    @Test
    public void logFileCanBeCreated() {
        File file = new File(fileName);
        TestCase.assertTrue(file.exists());
    }

    @Test
    public void getAllLogGivesEmptyListAfterCreatingLog() {
        TestCase.assertEquals(0, logger.getAllLog().size());
    }

    private void assertLogLastMessageIs(String str) {
        List<String> log = logger.getAllLog();
        assertEquals(true, log.size() != 0);
        String lastMes = log.get(log.size() - 1);
        assertTrue(lastMes.contains(str));
    }

    @Test
    public void logInfoMessageCanBeWritten() {
        String message = "This is the info log message!";
        logger.addInfo(message);
        assertLogLastMessageIs(message);
    }

    @Test
    public void logWarningMessageCanBeWritten() {
        String message = "This is the warning log message!";
        logger.addWarning(message);
        assertLogLastMessageIs(message);
    }

    @Test
    public void logErrorMessageCanBeWritten() {
        String message = "This is the error log message!";
        logger.addError(message);
        assertLogLastMessageIs(message);
    }

    @Test
    public void severalMessagesCanBeWritten() {
        String message = "This is the error log message!";
        logger.addError(message + "1");
        logger.addError(message + "2");
        TestCase.assertEquals(2, logger.getAllLog().size());
    }
}
