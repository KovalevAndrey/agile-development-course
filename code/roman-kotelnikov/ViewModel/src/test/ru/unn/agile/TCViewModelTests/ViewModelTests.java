package ru.unn.agile.TCViewModelTests;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

import org.junit.rules.ExpectedException;
import ru.unn.agile.TC.viewmodel.ILogger;
import ru.unn.agile.TC.viewmodel.ViewModel;

import java.io.IOException;

import static ru.unn.agile.TC.AvailableScales.*;
import static ru.unn.agile.TC.viewmodel.ViewModel.Status.*;

public class ViewModelTests {
    private ViewModel viewModel;
    private ILogger logger;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() {
        logger = new FakeLogger();
        viewModel = new ViewModel(logger);
    }

    @Test
    public void canInitializeWithDefaults() throws IOException {
        assertEquals("0.0", viewModel.input);
        assertEquals(Celsius, viewModel.inputScale);
        assertEquals("", viewModel.result);
        assertEquals(Fahrenheit, viewModel.resultScale);

        assertEquals(0, logger.getLog().size());
    }

    @Test
    public void canConvertFromCelsiusToFahrenheit() {
        viewModel.input = "36.5";
        viewModel.convert();

        assertEquals("97.7 F", viewModel.result);
    }

    @Test
    public void canHandleEmptyInputString() {
        viewModel.input = "";
        viewModel.convert();

        assertEquals(STATUS_WRONG_INPUT_STRING, viewModel.status);
    }

    @Test
    public void canHandleWrongInputString() {
        viewModel.input = "o-lo-lo";

        viewModel.convert();

        assertEquals(STATUS_WRONG_INPUT_STRING, viewModel.status);
    }

    @Test
    public void canHandleNullInputScale() {
        viewModel.inputScale = null;

        viewModel.convert();

        assertEquals(STATUS_INPUT_SCALE_NULL, viewModel.status);
    }

    @Test
    public void canHandleNullResultScale() {
        viewModel.resultScale = null;

        viewModel.convert();

        assertEquals(STATUS_RESULT_SCALE_NULL, viewModel.status);
    }

    @Test
    public void canHandleNullLogger() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Logger cannot be null");
        viewModel = new ViewModel(null);
    }
}
