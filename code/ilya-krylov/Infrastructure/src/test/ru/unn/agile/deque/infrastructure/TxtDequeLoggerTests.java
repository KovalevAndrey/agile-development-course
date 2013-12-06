package ru.unn.agile.deque.infrastructure;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.*;

public class TxtDequeLoggerTests {

    private TxtLogger txtLogger;
    private static final String filename = "TxtDequeLoggerTests.log";

    @Before
    public void setUp() {
        txtLogger = new TxtLogger(filename);
    }

    @Test
    public void canCreateLoggerWithFileName() {
        assertNotNull(txtLogger);
    }

    @Test
    public void isLogFileCreatedOnDisk() {
        try {
            new BufferedReader(new FileReader(filename));
        } catch (FileNotFoundException e) {
            System.out.println(new File(filename).getAbsolutePath());
            fail("Cannot find file with name \"" + filename + "\"");
        }
    }

    @Test
    public void canWriteMessageToLogFile(){
        String message = "Hello world!";
        txtLogger.log(message);
        Pattern pattern = Pattern.compile(".*" + message + "$");
        Matcher matcher = pattern.matcher(txtLogger.getLog().get(txtLogger.getLog().size() - 1));
        assertTrue(matcher.matches());
    }

    @Test
    public void canWriteGroupOfMessagesToLogFile(){
        String messages[] = {"Hello world!", "Good bye world!"};
        txtLogger.log(messages[0]);
        txtLogger.log(messages[1]);
        List<String> log = txtLogger.getLog();

        for (int i = 0; i < log.size(); i++){
            Pattern pattern = Pattern.compile(".*" + messages[i] + "$");
            Matcher matcher = pattern.matcher(txtLogger.getLog().get(i));
            assertTrue(matcher.matches());
        }
    }

    @Test
    public void doesLogContainDate(){
        String message = "Hello world!";
        txtLogger.log(message);
        Pattern pattern = Pattern.compile("^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}: " + message + "$");
        Matcher matcher = pattern.matcher(txtLogger.getLog().get(txtLogger.getLog().size() - 1));
        assertTrue(matcher.matches());
    }
}
