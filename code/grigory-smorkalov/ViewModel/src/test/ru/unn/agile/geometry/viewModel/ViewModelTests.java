package ru.unn.agile.geometry.viewModel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.unn.agile.geometry.Point;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ViewModelTests {
    protected LinePlaneIntersection viewModel;
    protected ILogger logger;

    @Before
    public void setUp() {
        logger = new FakeLogger();
        viewModel = new LinePlaneIntersection(logger);
    }

    @After
    public void tearDown() {
        viewModel = null;
    }

    @Test
    public void canSetDefaultValues() {
        assertEquals(viewModel.getLinePx(), "");
        assertEquals(viewModel.getLinePy(), "");
        assertEquals(viewModel.getLinePz(), "");
        assertEquals(viewModel.getLineDirX(), "");
        assertEquals(viewModel.getLineDirY(), "");
        assertEquals(viewModel.getLineDirZ(), "");
        assertEquals(viewModel.getPlanePointX(), "");
        assertEquals(viewModel.getPlanePointY(), "");
        assertEquals(viewModel.getPlanePointZ(), "");
        assertEquals(viewModel.getPlaneOrtX(), "");
        assertEquals(viewModel.getPlaneOrtY(), "");
        assertEquals(viewModel.getPlaneOrtZ(), "");
        assertEquals(viewModel.isCalcButtonEnabled(), false);
    }

    @Test
    public void whenInputIsIncorrectButtonDisabled() {
        fillWithCorrectData();
        viewModel.setLinePx("lpx");

        viewModel.inputSomething();

        assertEquals(viewModel.isCalcButtonEnabled(), false);
        assertEquals(viewModel.getResultX(), "Parse error");
    }

    @Test
    public void whenInputIsCorrectButtonEnabled() {
        fillWithCorrectData();

        viewModel.inputSomething();

        assertEquals(viewModel.isCalcButtonEnabled(), true);
    }

    @Test
    public void canConvertToPoint() {
        String x = "0";
        String y = "-1.0";
        String z = "2e-1";

        Point result = viewModel.parsePoint(x, y, z);

        assertEquals(result, new Point(0, -1.0, 0.2));
    }

    @Test
    public void canConvertToPointSpecific() {
        String x = " 1.2e0";
        String y = "100 ";
        String z = " 6";

        Point result = viewModel.parsePoint(x, y, z);

        assertEquals(result, new Point(1.2, 100, 6));
    }

    @Test
    public void whenIntersectionExistSetResult() {
        fillWithCorrectData();

        viewModel.inputSomething();
        viewModel.calc();

        Point result = viewModel.parsePoint(viewModel.getResultX(), viewModel.getResultY(), viewModel.getResultZ());

        assertEquals(result, new Point(0.0, 1.0, 2.0));
    }

    @Test
    public void whenClearResultsOnInput() {
        fillWithCorrectData();
        viewModel.setPlaneOrtZ("");

        viewModel.inputSomething();

        viewModel.setPlaneOrtZ("1.0");

        viewModel.inputSomething();
        assertEquals(viewModel.getResultX(), "");
    }

    @Test
    public void whenIntersectionNotExistSetMessage() {
        fillWithCorrectDataNoIntersection();

        viewModel.inputSomething();
        viewModel.calc();

        assertEquals(viewModel.getResultX(), "no intersection");
        assertEquals(viewModel.getResultY(), "no intersection");
        assertEquals(viewModel.getResultZ(), "no intersection");
    }

    @Test
    public void whenCreateLogIsEmpty() {
        assertTrue(logger.getLog().isEmpty());
    }

    @Test
    public void whenUserInputLogValues() {
        viewModel.setLinePx("lpx");
        viewModel.setLinePy("lpy");
        viewModel.setLinePz("lpz");
        viewModel.setLineDirX("dirX");
        viewModel.setLineDirY("dirY");
        viewModel.setLineDirZ("dirZ");
        viewModel.setPlanePointX("ppx");
        viewModel.setPlanePointY("ppy");
        viewModel.setPlanePointZ("ppz");
        viewModel.setPlaneOrtX("ortX");
        viewModel.setPlaneOrtY("ortY");
        viewModel.setPlaneOrtZ("ortZ");

        viewModel.inputSomething();

        String supposedLog = ILogger.MESSAGE_PREFIX
                + ": INPUT: lineP{lpx,lpy,lpz};lineDir{dirX,dirY,dirZ};planeP{ppx,ppy,ppz};planeOrt{ortX,ortY,ortZ}";

        assertEquals(logger.getLog().get(0), supposedLog);
    }

    @Test
    public void whenParseErrorLogMessage() {
        fillWithCorrectData();
        viewModel.setLinePx("lpx");

        viewModel.inputSomething();

        String supposedLog = ILogger.MESSAGE_PREFIX
                + ": PARSE ERROR: For input string: \"lpx\"";

        List<String> log = logger.getLog();
        assertFalse(logger.getLog().isEmpty());
        assertEquals(log.get(log.size() - 1), supposedLog);
    }

    @Test
    public void whenButtonActivateLog() {
        fillWithCorrectData();

        viewModel.inputSomething();

        String supposedLog = ILogger.MESSAGE_PREFIX
                + ": BUTTON ENABLE SET: true";

        List<String> log = logger.getLog();
        assertEquals(log.get(log.size() - 1), supposedLog);
    }

    @Test
    public void whenButtonDeactivateLog() {
        fillWithCorrectData();

        viewModel.inputSomething();
        viewModel.setPlaneOrtZ("");
        viewModel.inputSomething();

        String supposedLog = supposedLog = ILogger.MESSAGE_PREFIX
                + ": BUTTON ENABLE SET: false";

        List<String> log = logger.getLog();
        assertEquals(log.get(log.size() - 1), supposedLog);
    }

    @Test
    public void whenCalculateLogParsedInput() {
        fillWithCorrectData();

        viewModel.inputSomething();
        viewModel.calc();

        String supposedLog = ILogger.MESSAGE_PREFIX
                + ": PARSED INPUT: lineP{1.0,2.0,3.0};lineDir{1.0,1.0,1.0};planeP{0.0,0.0,0.0};planeOrt{1.0,0.0,0.0}";

        List<String> log = logger.getLog();
        assertEquals(log.get(log.size() - 2), supposedLog);
    }

    @Test
    public void whenCalculateLogResults() {
        fillWithCorrectData();

        viewModel.inputSomething();
        viewModel.calc();

        String supposedLog = ILogger.MESSAGE_PREFIX
                + ": RESULT: {0.0,1.0,2.0}";

        List<String> log = logger.getLog();
        assertFalse(logger.getLog().isEmpty());
        assertEquals(log.get(log.size() - 1), supposedLog);
    }

    @Test
    public void whenCalculateLogNullResults() {
        fillWithCorrectDataNoIntersection();

        viewModel.inputSomething();
        viewModel.calc();

        String supposedLog = ILogger.MESSAGE_PREFIX
                + ": RESULT: {NULL}";

        List<String> log = logger.getLog();
        assertFalse(logger.getLog().isEmpty());
        assertEquals(log.get(log.size() - 1), supposedLog);
    }

    @Test
    public void whenWrongInputExceptionDebugLog() {
        String separator = System.lineSeparator();
        logger.message("message");
        logger.debug("message" + separator + "with" + separator + "several" + separator + "lines");
        logger.message("last message");

        String log = viewModel.getLog();
        String expected = ILogger.MESSAGE_PREFIX + ": message" + separator +
                ILogger.DEBUG_PREFIX +  ": message" + separator + "with" + separator + "several" + separator + "lines" + separator +
                ILogger.MESSAGE_PREFIX + ": last message" + separator;
        assertEquals(expected, log);
    }

    @Test
    public void canConvertLogToString() {
        viewModel.setLinePx("q");

        viewModel.inputSomething();

        List<String> log = logger.getLog();
        assertTrue(log.get(log.size() - 2).contains(ILogger.DEBUG_PREFIX));
    }

    private void fillWithCorrectData() {
        viewModel.setLinePx("1");
        viewModel.setLinePy("2");
        viewModel.setLinePz("3");
        viewModel.setLineDirX("1");
        viewModel.setLineDirY("1");
        viewModel.setLineDirZ("1");
        viewModel.setPlanePointX("0");
        viewModel.setPlanePointY("0");
        viewModel.setPlanePointZ("0");
        viewModel.setPlaneOrtX("1");
        viewModel.setPlaneOrtY("0");
        viewModel.setPlaneOrtZ("0");
    }

    private void fillWithCorrectDataNoIntersection() {
        fillWithCorrectData();
        viewModel.setLineDirX("0");
    }
}
