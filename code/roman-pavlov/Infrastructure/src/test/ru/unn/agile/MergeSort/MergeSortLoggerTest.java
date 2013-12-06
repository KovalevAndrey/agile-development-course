package ru.unn.agile.MergeSort;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class MergeSortLoggerTest {

    public static final String LOG_FILE = "./MergeSortLoggerTest.log";
    private MergeSortLogger logger;

    @Before
    public void setUp() {
        logger = new MergeSortLogger(LOG_FILE);
    }

    @After
    public void tearDown() {
        logger = null;
    }

    @Test
    public void canCreateLog() {
        assertNotNull(logger);
    }

    void assertFindedInString(String in, String what) {
        int pos = in.indexOf(what);
        assertTrue(pos >= 0);
    }

    @Test
    public void canWriteToLog() {
        logger.log("test test");
        assertFindedInString(logger.getLog().get(logger.getLog().size() - 1),
                "test test");
    }

    @Test
    public void canWrite2MessagesToLog() {
        logger.log("test1");
        logger.log("test2");
        assertFindedInString(logger.getLog().get(logger.getLog().size() - 2),
                "test1");
        assertFindedInString(logger.getLog().get(logger.getLog().size() - 1),
                "test2");
    }

    @Test
    public void logReallyWriteToFile() {
        List<String> inFile = logger.getLog();
        List<String> inLog = new ArrayList<String>();
        BufferedReader bf;
        try {
            bf = new BufferedReader(new FileReader(LOG_FILE));
            String line = bf.readLine();
            while (line != null) {
                inFile.add(line);
            }
            assertEquals(inFile.size(),inLog.size());
            for (int i=0; i<inFile.size(); i++)
            {
                assertEquals(inFile.get(i),inLog.get(i));
            }
        } catch (FileNotFoundException e) {
            fail("File not found\n" + e.toString());
        } catch (IOException e) {
            fail("Not access to file\n" + e.toString());
        }
    }
}
