package ru.unn.agile.VolumeCalculator.Infrastructure;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TxtLoggerTests {
    private static final String filename = "./VolumeCalculatorLogger.log";
    private TxtLogger txtLogger;

    @Before
    public void beforeTest() {
        txtLogger = new TxtLogger(filename);
    }

    @Test
    public void txtLoggerCanBeCreated() {
        assertNotNull(txtLogger);
    }

    @Test
    public void logFileCanBeCreated() {
        try {
            new BufferedReader(new FileReader(filename));
        } catch (FileNotFoundException e) {
            fail("File " + filename + " wasn't found!");
        }
    }

    @Test
    public void txtLogCanBeGet() {
        assertNotNull(txtLogger.getAllMessages());
    }

    @Test
    public void lastMassageFromTxtLogCanBeGet() {
        assertNotNull(txtLogger.getLastMessage());
    }

    @Test
    public void recordGetFromFileLogAndFromLoggerAreEquals(){
        txtLogger.logInfo("Example");
        assertLists(readLogFile(), txtLogger.getAllMessages());
    }

    @Test
    public void afterAddRecordSizeLogMustBeIncreased() {
        int startSize=txtLogger.getAllMessages().size();
        txtLogger.logInfo("Example");
        int finishSize=txtLogger.getAllMessages().size();
        assertTrue(finishSize==startSize+1);
    }

    @Test
    public void messageWriteToLoggerCorrect() {
        String message = "Example";
        txtLogger.logInfo(message);
        assertEquals(true, txtLogger.getLastMessage().indexOf("Example")>0);
    }

    private void assertLists(List<String> expected, List<String> actual) {
        assertArrayEquals(expected.toArray(), actual.toArray());
    }

    private List<String> readLogFile() {
        BufferedReader reader;
        ArrayList<String> logList = new ArrayList<String>();
        try {
            reader = new BufferedReader(new FileReader(filename));
            String line = reader.readLine();

            while (line != null) {
                logList.add(line);
                line = reader.readLine();
            }
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return logList;
    }
}
