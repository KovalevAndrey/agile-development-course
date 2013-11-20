package ru.unn.agile.TCInfrastructureTests;

import org.junit.Before;

import ru.unn.agile.TC.infrastructure.TxtLogger;
import ru.unn.agile.TCViewModelTests.CommonLoggerTests;

public class TxtLoggerTests extends CommonLoggerTests {

    @Before
    public void setUp() {
        logger = new TxtLogger("TxtLoggerTestLog.txt");
    }
}
