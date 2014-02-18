package ru.unn.agile.VolumeCalculator.Infrastructure;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
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
            BufferedReader reader = new BufferedReader(new FileReader(filename));
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Test
    public void txtLogCanBeGet() {
        assertNotNull(txtLogger.getAllMessage());
    }

    @Test
    public void lastMassageFromTxtLogCanBeGet() {
        assertNotNull(txtLogger.getLastMessage());
    }

    @Test
    public void RecordGetFromFileLogAndFromLoggerAreEquals(){
        txtLogger.logInfo("Example");
        assertLists(readLogFile(), txtLogger.getAllMessage());
    }

    @Test
    public void afterAddRecordSizeLogMustBeIncreased() {
        int startSize=txtLogger.getAllMessage().size();
        txtLogger.logInfo("Example");
        int finishSize=txtLogger.getAllMessage().size();
        assertTrue(finishSize>startSize);
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
