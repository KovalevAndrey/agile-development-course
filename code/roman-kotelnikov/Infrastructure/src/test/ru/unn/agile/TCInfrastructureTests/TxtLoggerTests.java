package ru.unn.agile.TCInfrastructureTests;

import org.junit.Before;

import org.junit.Test;
import ru.unn.agile.TC.infrastructure.TxtLogger;
import ru.unn.agile.TCViewModelTests.CommonLoggerTests;

import java.io.IOException;

public class TxtLoggerTests extends CommonLoggerTests {

    @Before
    public void setUp() throws IOException{
        logger = new TxtLogger("TxtLoggerTestLog.txt");
    }

    @Test
    public void ensureEmptyFilenameExceptionThrown() throws IOException{
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("File name cannot be empty");
        logger = new TxtLogger("");
    }

    @Test
    public void ensureNullFileNameExceptionThrown() throws IOException{
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("File name cannot be null");
        logger = new TxtLogger(null);
    }
}
