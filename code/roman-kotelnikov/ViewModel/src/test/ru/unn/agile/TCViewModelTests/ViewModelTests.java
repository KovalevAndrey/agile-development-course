package ru.unn.agile.TCViewModelTests;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import ru.unn.agile.TC.viewmodel.ViewModel;
import static ru.unn.agile.TC.AvailableScales.*;
import static ru.unn.agile.TC.viewmodel.ViewModel.Status.*;

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
    public void emptyInputString() {
        viewModel.input = "";
        viewModel.convert();

        assertEquals(WRONG_INPUT_STRING, viewModel.status);
    }

    @Test
    public void wrongInputString() {
        viewModel.input = "o-lo-lo";
        viewModel.convert();

        assertEquals(WRONG_INPUT_STRING, viewModel.status);
    }

    @Test
    public void nullInputScale() {
        viewModel.inputScale = null;
        viewModel.convert();

        assertEquals(INPUT_SCALE_NULL, viewModel.status);
    }

    @Test
    public void nullResultScale() {
        viewModel.resultScale = null;
        viewModel.convert();

        assertEquals(RESULT_SCALE_NULL, viewModel.status);
    }
}
