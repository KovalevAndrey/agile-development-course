package ru.unn.agile.AreaConverter.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static ru.unn.agile.AreaConverter.viewmodel.ViewModel.Status.*;

public class ViewModelTests {
    private ViewModel viewModel;

    @Before
    public void setUp() {
        viewModel = new ViewModel();
    }

    @Test
    public void canInitializeDefaults() {
        assertEquals("0.0", viewModel.input);
        assertEquals("Metre", viewModel.inputScale);
        assertEquals("", viewModel.result);
        assertEquals("Hectare", viewModel.resultScale);
    }

    @Test
    public void canConvertFromMetreToHectare() {
        viewModel.input = "10000.0";
        viewModel.convert();

        assertEquals("1.0", viewModel.result);
    }

    @Test
    public void emptyInputArea() {
        viewModel.input = "";
        viewModel.convert();

        assertEquals(INCORRECT_INPUT_AREA, viewModel.status);
    }

    @Test
    public void incorrectInputArea() {
        viewModel.input = "Incorrect Area String";
        viewModel.convert();

        assertEquals(INCORRECT_INPUT_AREA, viewModel.status);
    }

    @Test
    public void negativeInputArea() {
        viewModel.input = "-1.0";
        viewModel.convert();

        assertEquals(NEGATIVE_INPUT_AREA, viewModel.status);
    }

    @Test
    public void incorrectInputScale() {
        viewModel.inputScale = "ololo ololo";
        viewModel.convert();

        assertEquals(INCORRECT_INPUT_SCALE, viewModel.status);
    }

    @Test
    public void incorrectOutputScale() {
        viewModel.resultScale = "I'm UFO driver";
        viewModel.convert();

        assertEquals(INCORRECT_OUTINPUT_SCALE, viewModel.status);
    }
}
