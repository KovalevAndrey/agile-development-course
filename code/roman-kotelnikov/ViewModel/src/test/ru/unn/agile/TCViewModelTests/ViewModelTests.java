package ru.unn.agile.TCViewModelTests;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import ru.unn.agile.TC.viewmodel.ILogger;
import ru.unn.agile.TC.viewmodel.ViewModel;
import static ru.unn.agile.TC.AvailableScales.*;
import static ru.unn.agile.TC.viewmodel.ILogger.LoggerConstant.*;
import static ru.unn.agile.TC.viewmodel.ViewModel.Status.*;

public class ViewModelTests {
    private ViewModel viewModel;

    @Before
    public void setUp() {
        viewModel = new ViewModel(new FakeLogger());
    }

    @Test
    public void canInitializeWithDefaults() {
        assertEquals("0.0", viewModel.input);
        assertEquals(Celsius, viewModel.inputScale);
        assertEquals("", viewModel.result);
        assertEquals(Fahrenheit, viewModel.resultScale);

        assertEquals(0, viewModel.getLogger().getLog().size());
    }

    @Test
    public void canConvertFromCelsiusToFahrenheit() {
        viewModel.input = "36.5";
        checkUserInputsLogging();

        viewModel.convert();
        checkStatusLogging(LOG_VIEW_MODEL_OK);

        assertEquals("97.7 F", viewModel.result);
    }

    @Test
    public void emptyInputString() {
        viewModel.input = "";
        checkUserInputsLogging();

        viewModel.convert();
        checkStatusLogging(LOG_ERROR_WRONG_INPUT_STRING.toString());

        assertEquals(STATUS_WRONG_INPUT_STRING, viewModel.status);
    }

    @Test
    public void wrongInputString() {
        viewModel.input = "o-lo-lo";
        checkUserInputsLogging();

        viewModel.convert();

        assertEquals(STATUS_WRONG_INPUT_STRING, viewModel.status);
        checkStatusLogging(LOG_ERROR_WRONG_INPUT_STRING.toString());
    }

    @Test
    public void nullInputScale() {
        viewModel.inputScale = null;
        checkUserInputsLogging();

        viewModel.convert();
        checkStatusLogging(LOG_ERROR_INPUT_SCALE_IS_NULL.toString());

        assertEquals(STATUS_INPUT_SCALE_NULL, viewModel.status);
    }

    @Test
    public void nullResultScale() {
        viewModel.resultScale = null;
        checkUserInputsLogging();

        viewModel.convert();
         checkStatusLogging(LOG_ERROR_RESULT_SCALE_IS_NULL.toString());

        assertEquals(STATUS_RESULT_SCALE_NULL, viewModel.status);
    }

    public void checkUserInputsLogging() {
        viewModel.inputFocusLost();

        String message = String.format(LOG_INPUT_MESSAGE,
                viewModel.input,
                viewModel.inputScale,
                viewModel.resultScale);

        assertTrue(viewModel.getLogger().getLastMessage().contains(message));
    }

    public void checkStatusLogging(String message) {
        String loggerLastMessage = viewModel.getLogger().getLastMessage();
        assertTrue(loggerLastMessage.matches(message));
    }
}
