package ru.unn.agile.TCInfrastructureTests;

import org.junit.Before;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import ru.unn.agile.TC.infrastructure.TxtLogger;
import ru.unn.agile.TCViewModelTests.CommonLoggerTests;

public class TxtLoggerTests extends CommonLoggerTests {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() {
        logger = new TxtLogger("TxtLoggerTestLog.txt");
    }

    @Test
    public void canHandleEmptyFileName() {
        exception.expect(IllegalArgumentException.class);
        logger = new TxtLogger("");
    }

    @Test
    public void canHandleNullFileName() {
        exception.expect(IllegalArgumentException.class);
        logger = new TxtLogger(null);
    }
}
