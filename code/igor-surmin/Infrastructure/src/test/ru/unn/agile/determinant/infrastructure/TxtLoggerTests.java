package ru.unn.agile.determinant.infrastructure;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertNotNull;

public class TxtLoggerTests {
    private static final String filename = "./TxtLoggerTests.log";
    private TxtLogger txtLogger;

    @Before
    public void setUp() {
        txtLogger = new TxtLogger(filename);
    }

    @Test
    public void canCreateLoggerWithFileName() {
        assertNotNull(txtLogger);
    }

    @Test
    public void canCreateFile() {
        try {
            new BufferedReader(new FileReader(filename));
        }
        catch (FileNotFoundException e) {
            fail("File " + filename + " wasn't found!");
        }
    }

    @Test
    public void canWriteMessage() {
        String testMsg = "Test message";

        txtLogger.logMessage(testMsg);

        String msg = readLines().get(0);
        assertTrue(msg.indexOf(testMsg) >= 0);
    }

    @Test
    public void canWriteManyLines() {
        String[] msg = {"Log Message 1", "Log message 2"};

        txtLogger.logMessage(msg[0]);
        txtLogger.logMessage(msg[1]);

        List<String> actualMessages = readLines();
        for (int i = 0; i < actualMessages.size(); i++)
            assertTrue(actualMessages.get(i).indexOf(msg[i]) >= 0);
    }

    private List<String> readLines() {
        BufferedReader reader;
        ArrayList<String> log = new ArrayList<String>();
        try {
            reader = new BufferedReader(new FileReader(filename));
            String str = reader.readLine();

            while (str != null) {
                log.add(str);
                str = reader.readLine();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return log;
    }
}
