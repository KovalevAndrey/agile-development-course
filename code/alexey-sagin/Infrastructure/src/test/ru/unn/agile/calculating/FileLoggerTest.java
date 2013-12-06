package ru.unn.agile.calculating;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class FileLoggerTest {

    FileLogger log;

    @Before
    public void beforeTest() {
        log = new FileLogger("./log_test.log");
    }

    @After
    public void afterTest() {
        log = null;
    }

    @Test
    public void canCreateLog() {
        assertNotNull(log);
    }

    @Test
    public void logIsEmptyAfterCreating() {
        assertEquals(0, log.getAllMessages().size());
    }

    @Test
    public void canWriteToLog() {
        log.putMessage("test");
        boolean ok = 0 <= log.getLastMessage().indexOf("test");
        assertEquals(true, ok);
    }

    @Test
    public void canLogIfWrongFile() {
        log = new FileLogger(">>??./..:@#$%^&");
        log.putMessage(" asd ");
        assertEquals(true, !log.getLastMessage().equals(""));
    }

    @Test
    public void canCreateLogWithFile() {
        log = new FileLogger("./test2.log");
        log.putMessage("asd");
        assertEquals(true, new File("./test2.log").exists());
    }

    @Test
    public void canWriteToLog2Lines() {
        log.putMessage("test");
        log.putMessage("test 2");
        assertEquals(2, log.getAllMessages().size());
    }
}
