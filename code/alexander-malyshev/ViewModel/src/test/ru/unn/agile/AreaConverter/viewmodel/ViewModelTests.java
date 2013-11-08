package ru.unn.agile.AreaConverter.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.unn.agile.areaConverter.ScaleTable;

import static org.junit.Assert.*;
import static ru.unn.agile.AreaConverter.viewmodel.ViewModel.Status.*;

public class ViewModelTests {
    private ViewModel viewModel;
    private final double delta = 1e-7;

    @Before
    public void setUp() {
        viewModel = new ViewModel();
    }

    @Test
    public void canInitializeDefaults() {
        assertEquals("0.0", viewModel.input);
        assertEquals("Metre", viewModel.inputScale.name());
        assertEquals("", viewModel.result);
        assertEquals("Hectare", viewModel.resultScale.name());
    }

    @Test
    public void canConvertFromMetreToHectare() {
        viewModel.input = "10000.0";
        viewModel.convert();

        assertEquals("1.0", viewModel.result);
    }

    @Test
    public void canConvertEmptyInputArea() {
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
    public void nullInputScale() {
        viewModel.inputScale = null;
        viewModel.convert();

        assertEquals(NULL_INPUT_SCALE, viewModel.status);
    }

    @Test
    public void nullResultScale() {
        viewModel.resultScale = null;
        viewModel.convert();

        assertEquals(NULL_RESULT_SCALE, viewModel.status);
    }

    @Test
    public void canConvertFromHectareToYard() {
        viewModel.inputScale = ScaleTable.Hectare;
        viewModel.input = "123.0";
        viewModel.resultScale = ScaleTable.Yard;
        viewModel.convert();

        assertEquals(1471067.7569503288, Double.parseDouble(viewModel.result), delta);
    }

    @Test
    public void canConvertFromYardToCentimetre() {
        viewModel.inputScale = ScaleTable.Yard;
        viewModel.input = "3.141593653";
        viewModel.resultScale = ScaleTable.Centimetre;
        viewModel.convert();

        assertEquals(26267.72407275645, Double.parseDouble(viewModel.result), delta);
    }
}
