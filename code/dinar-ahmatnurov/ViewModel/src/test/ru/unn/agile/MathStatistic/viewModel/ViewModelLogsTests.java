package ru.unn.agile.MathStatistic.viewModel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class ViewModelLogsTests {
    private final float EPS = 1e-5f;
    protected ViewModel viewModel;

    @Before
    public void setUp() {
        viewModel = new ViewModel(new FakeLogger());
    }

    @After
    public void tearDown() {
        viewModel = null;
    }

    @Test
    public void isLogOKOnEmptyInput() {
        viewModel.setInputData("");
        viewModel.convertToArrayOfDoubles();
        ArrayList<String> actLog = viewModel.getEntireLog();
        assertEquals(3, actLog.size());
        String logItem = actLog.get(2);
        assertEquals(true, logItem.matches(".*state:\\[\\|Expected value\\|\\|Provide input data\\|disabled\\]"));

    }

    @Test
    public void isInitialLogMessageOK() {
        ArrayList<String> actLog = viewModel.getEntireLog();
        assertEquals(2, actLog.size());
        String logItem = actLog.get(0);
        assertEquals(true, logItem.matches(".*Program successfully started"));
        logItem = actLog.get(1);
        assertEquals(true, logItem.matches(".*state:\\[\\|Expected value\\|\\|Provide input data\\|disabled\\]"));
    }

    @Test
    public void isLogOkOnInputWithLetters() {
        viewModel.setInputData("12 3 a");
        viewModel.convertToArrayOfDoubles();
        ArrayList<String> actLog = viewModel.getEntireLog();
        assertEquals(3, actLog.size());
        String logItem = actLog.get(2);
        assertEquals(true, logItem.matches(".*state:\\[12 3 a\\|Expected value\\|\\|Bad input data\\|disabled\\]"));
    }

    @Test
    public void isLogOkOnInputWithNonLetters() {
        viewModel.setInputData("17.17 -100 ?");
        viewModel.convertToArrayOfDoubles();
        ArrayList<String> actLog = viewModel.getEntireLog();
        assertEquals(3, actLog.size());
        String logItem = actLog.get(2);
        assertEquals(true, logItem.matches(".*state:\\[17.17 -100 \\?\\|Expected value\\|\\|Bad input data\\|disabled\\]"));
    }

    @Test
    public void isLogOkOnDuplicatedWhitespace() {
        viewModel.setInputData("  17.17   -100.1  44.4  ");
        viewModel.convertToArrayOfDoubles();
        ArrayList<String> actLog = viewModel.getEntireLog();
        assertEquals(3, actLog.size());
        String logItem = actLog.get(2);
        assertEquals(true, logItem.matches(".*state:\\[17.17 -100.1 44.4\\|Expected value\\|\\|Press 'Calc it!' or Enter\\|enabled\\]"));
    }

    @Test
    public void isLogOkOnBatchPositiveNumbers() {
        viewModel.setInputData("1.1 0.2 1.003 100 107.7");
        viewModel.convertToArrayOfDoubles();
        ArrayList<String> actLog = viewModel.getEntireLog();
        assertEquals(3, actLog.size());
        String logItem = actLog.get(2);
        assertEquals(true, logItem.matches(".*state:\\[1.1 0.2 1.003 100 107.7\\|Expected value\\|\\|Press 'Calc it!' or Enter\\|enabled\\]"));
    }

    @Test
    public void isLogOkOnBatchNegativeNumber() {
        viewModel.setInputData("-1.02 -0.01 -200 -10.3 -1");
        viewModel.convertToArrayOfDoubles();
        ArrayList<String> actLog = viewModel.getEntireLog();
        assertEquals(3, actLog.size());
        String logItem = actLog.get(2);
        assertEquals(true, logItem.matches(".*state:\\[-1.02 -0.01 -200 -10.3 -1\\|Expected value\\|\\|Press 'Calc it!' or Enter\\|enabled\\]"));
    }

    @Test
    public void isLogOkOnMixedNumbers() {
        viewModel.setInputData("-1 1 -2.7 3.1 77.01 17.8 -100 2.1 87");
        viewModel.convertToArrayOfDoubles();
        ArrayList<String> actLog = viewModel.getEntireLog();
        assertEquals(3, actLog.size());
        String logItem = actLog.get(2);
        assertEquals(true, logItem.matches(".*state:\\[-1 1 -2.7 3.1 77.01 17.8 -100 2.1 87\\|Expected value\\|\\|Press 'Calc it!' or Enter\\|enabled\\]"));
    }

    @Test
    public void isLogOkOnWithGoodDataAndCalc() {
        viewModel.setInputData("-1 1 -2.7 3.1 17.8 -100 2.1 87");
        viewModel.processKeyInTextField(viewModel.ENTER_CODE);
        ArrayList<String> actLog = viewModel.getEntireLog();
        assertEquals(4, actLog.size());
        String logItem = actLog.get(3);
        assertEquals(true, logItem.matches(".*state:\\[-1 1 -2.7 3.1 17.8 -100 2.1 87\\|Expected value\\|0.9124994\\|Success\\|enabled\\]"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void isBehaviourOkWithNoLogger() {
        viewModel = new ViewModel(null);
    }

    @Test
    public void isLogCorrectOnChangedOperationAndNoData() {
        viewModel.setOperation(ViewModel.Statistic.FOURTH_CENTRAL_MOMENT);
        viewModel.processKeyInTextField(viewModel.ENTER_CODE);
        ArrayList<String> actLog = viewModel.getEntireLog();
        assertEquals(4, actLog.size());
        String logItem = actLog.get(3);
        assertEquals(true, logItem.matches(".*state:\\[\\|Fourth central moment\\|\\|Provide input data\\|disabled\\]"));
    }

    @Test
    public void isLogCorrectOnChangedOperationWithData() {
        viewModel.setInputData("-1 1 -2.7 3.1 17.8 -100 2.1 87");
        viewModel.setOperation(ViewModel.Statistic.FOURTH_CENTRAL_MOMENT);
        viewModel.processKeyInTextField(viewModel.ENTER_CODE);
        ArrayList<String> actLog = viewModel.getEntireLog();
        assertEquals(5, actLog.size());
        String logItem = actLog.get(4);
        assertEquals(true, logItem.matches(".*state:\\[-1 1 -2.7 3.1 17.8 -100 2.1 87\\|Fourth central moment\\|1.9838198E7\\|Success\\|enabled\\]"));
    }

    @Test
    public void isInfoTypeLogWorksCorrectly() {

        viewModel.setInputData("-1 1 -2.7 3.1 17.8 -100 2.1 87AA");
        viewModel.convertToArrayOfDoubles();

        viewModel.setInputData("-1 1 -2.7 3.1 17.8 -100 2.1 87");
        viewModel.convertToArrayOfDoubles();

        viewModel.setOperation(ViewModel.Statistic.FOURTH_CENTRAL_MOMENT);
        viewModel.processKeyInTextField(viewModel.ENTER_CODE);

        viewModel.setInputData("-1 1 -2.7 3.1 17.8 -100 2.1 87BB");
        viewModel.convertToArrayOfDoubles();

        ArrayList<String> actLog = viewModel.getParticularType(ILogger.MessageType.INFO);
        assertEquals(6, actLog.size());

        String logItem = actLog.get(0);
        assertEquals(true, logItem.matches(".*Program successfully started"));

        logItem = actLog.get(1);
        assertEquals(true, logItem.matches(".*state:\\[\\|Expected value\\|\\|Provide input data\\|disabled\\]"));

        logItem = actLog.get(2);
        assertEquals(true, logItem.matches(".*state:\\[-1 1 -2.7 3.1 17.8 -100 2.1 87\\|Expected value\\|\\|Press 'Calc it!' or Enter\\|enabled\\]"));

        logItem = actLog.get(3);
        assertEquals(true, logItem.matches(".*state:\\[-1 1 -2.7 3.1 17.8 -100 2.1 87\\|Fourth central moment\\|\\|Press 'Calc it!' or Enter\\|enabled\\]"));

        logItem = actLog.get(4);
        assertEquals(true, logItem.matches(".*state:\\[-1 1 -2.7 3.1 17.8 -100 2.1 87\\|Fourth central moment\\|\\|Press 'Calc it!' or Enter\\|enabled\\]"));

        logItem = actLog.get(5);
        assertEquals(true, logItem.matches(".*state:\\[-1 1 -2.7 3.1 17.8 -100 2.1 87\\|Fourth central moment\\|1.9838198E7\\|Success\\|enabled\\]"));

    }

    @Test
    public void isWarningsTypeLogWorksCorrectly() {

        viewModel.setInputData("-1 1 -2.7 3.1 17.8 -100 2.1 87AA");
        viewModel.convertToArrayOfDoubles();

        viewModel.setInputData("-1 1 -2.7 3.1 17.8 -100 2.1 87");
        viewModel.convertToArrayOfDoubles();

        viewModel.setOperation(ViewModel.Statistic.VARIANCE);
        viewModel.processKeyInTextField(viewModel.ENTER_CODE);

        viewModel.setInputData("-1 1 -2.7 3.1 17.8 -100 2.1 87BB");
        viewModel.convertToArrayOfDoubles();

        ArrayList<String> actLog = viewModel.getParticularType(ILogger.MessageType.WARNING);
        assertEquals(2, actLog.size());

        String logItem = actLog.get(0);
        assertEquals(true, logItem.matches(".*state:\\[-1 1 -2.7 3.1 17.8 -100 2.1 87AA\\|Expected value\\|\\|Bad input data\\|disabled\\]"));

        logItem = actLog.get(1);
        assertEquals(true, logItem.matches(".*state:\\[-1 1 -2.7 3.1 17.8 -100 2.1 87BB\\|Variance\\|\\|Bad input data\\|disabled\\]"));
    }
}
