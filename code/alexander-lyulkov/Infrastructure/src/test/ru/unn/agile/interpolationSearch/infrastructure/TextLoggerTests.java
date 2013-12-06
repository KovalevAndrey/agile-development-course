package ru.unn.agile.interpolationSearch.infrastructure;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.io.File;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.assertNotNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.fail;

/**
 * Created with IntelliJ IDEA.
 * User: sasha
 * Date: 12/5/13
 * Time: 10:55 PM
 * To change this template use File | Settings | File Templates.
 */
public class TextLoggerTests {
    private static final String logFilename = "./testLog.log";
    private TextLogger logger;

    @Before
    public void setUp() {
        try{
            File fileTemp = new File(logFilename);
            if (fileTemp.exists()){
                fileTemp.delete();
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        logger = new TextLogger(logFilename);
    }

    @Test
    public void canInitializeLogger() {
        assertNotNull(logger);
    }


    @Test
    public void canWriteNoteToLog() {
        String note = "Some text";

        logger.AddNote(note);

        assertEquals(1, logger.ReadLog().length);
    }

    @Test
    public void canReadFromLog() {
        String note = "Some text";

        logger.AddNote(note);

        String savedNote = logger.ReadLog()[0];
        assertTrue(savedNote.endsWith(note));
    }

    @Test
    public void canWriteSeveralNotes() {
        String[] notes = new String[2];
        notes[0] = "first note";
        notes[1] = "second note";

        logger.AddNote(notes[0]);
        logger.AddNote(notes[1]);

        String[] savedNotes = logger.ReadLog();
        assertTrue(savedNotes[0].endsWith(notes[0]));
        assertTrue(savedNotes[1].endsWith(notes[1]));
    }

}