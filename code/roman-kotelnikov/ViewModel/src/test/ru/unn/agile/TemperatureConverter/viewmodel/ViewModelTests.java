package ru.unn.agile.TemperatureConverter.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static ru.unn.agile.TemperatureConverter.AvailableScales.*;
import static ru.unn.agile.TemperatureConverter.viewmodel.ViewModel.Status.*;

public class ViewModelTests {
    private final double DELTA = 1e-4;
    private ViewModel viewModel;

    @Before
    public void setUp() {
        viewModel = new ViewModel();
    }

    @Test
    public void canInitializeWithDefaults() {
        assertEquals("", viewModel.input);
        assertNull(viewModel.inputScale);
        assertEquals("", viewModel.result);
        assertNull(viewModel.resultScale);
    }

    @Test
    public void canConvertFromCelsiusToFahrenheit() {
        viewModel.input = "36.5";
        viewModel.inputScale = Celsius;
        viewModel.resultScale = Fahrenheit;
        viewModel.convert();

        assertEquals("97.7 F", viewModel.result);
    }

    @Test
    public void wrongInputString() {
        viewModel.input = "o-lo-lo";
        viewModel.inputScale = Celsius;
        viewModel.resultScale = Fahrenheit;
        viewModel.convert();

        assertEquals(WRONG_INPUT_STRING, viewModel.status);
    }

    @Test
    public void inputScaleCannotBeNull() {
        viewModel.input = "36.5";
        viewModel.resultScale = Fahrenheit;
        viewModel.convert();

        assertEquals(INPUT_SCALE_NULL, viewModel.status);
    }

    @Test
    public void resultScaleCannotBeNull() {
        viewModel.input = "36.5";
        viewModel.inputScale = Celsius;
        viewModel.convert();

        assertEquals(RESULT_SCALE_NULL, viewModel.status);
    }
}
