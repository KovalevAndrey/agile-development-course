package ru.unn.agile.TemperatureConverter.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static ru.unn.agile.TemperatureConverter.AvailableScales.*;
import static ru.unn.agile.TemperatureConverter.viewmodel.ViewModel.Status.*;

public class ViewModelTests {
    private ViewModel viewModel;

    @Before
    public void setUp() {
        viewModel = new ViewModel();
    }

    @Test
    public void canInitializeWithDefaults() {
        assertEquals("0.0", viewModel.input);
        assertEquals(Celsius, viewModel.inputScale);
        assertEquals("", viewModel.result);
        assertEquals(Fahrenheit, viewModel.resultScale);
    }

    @Test
    public void canConvertFromCelsiusToFahrenheit() {
        viewModel.input = "36.5";
        viewModel.convert();

        assertEquals("97.7 F", viewModel.result);
    }

    @Test
    public void wrongInputString() {
        viewModel.input = "o-lo-lo";
        viewModel.convert();

        assertEquals(WRONG_INPUT_STRING, viewModel.status);
    }
}
