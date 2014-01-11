package ru.unn.agile.geometry.viewModel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ViewModelTests {
    private LinePlainIntersection viewModel;

    @Before
    public void setUp() {
        viewModel = new LinePlainIntersection();
    }

    @After
    public void tearDown() {
        viewModel = null;
    }

    @Test
    public void canSetDefaultValues() {
        assertEquals(viewModel.lineP1X, "");
        assertEquals(viewModel.lineP1Y, "");
        assertEquals(viewModel.lineP2X, "");
        assertEquals(viewModel.lineP2Y, "");
        assertEquals(viewModel.plainPointX, "");
        assertEquals(viewModel.plainPointY, "");
        assertEquals(viewModel.plainOrtX, "");
        assertEquals(viewModel.plainOrtY, "");
        assertEquals(viewModel.isCalcButtonEnabled, false);
    }

    @Test
    public void whenInputIsIncorrectButtonDisabled() {
        viewModel.lineP1X = "lineP1X";
        viewModel.lineP1Y = "lineP1Y";
        viewModel.lineP2X = "lineP2X";
        viewModel.lineP2Y = "lineP2Y";
        viewModel.plainPointX = "plainPointX";
        viewModel.plainPointY = "plainPointY";
        viewModel.plainOrtX = "plainOrtX";
        viewModel.plainOrtY = "plainOrtY";

        viewModel.inputSomething();

        assertEquals(viewModel.isCalcButtonEnabled, false);
    }

    @Test
    public void whenInputIsCorrectButtonEnabled() {
        viewModel.lineP1X = "1";
        viewModel.lineP1Y = "1.0";
        viewModel.lineP2X = "0";
        viewModel.lineP2Y = "0.0";
        viewModel.plainPointX = "0.0";
        viewModel.plainPointY = "0.0";
        viewModel.plainOrtX = "0.0";
        viewModel.plainOrtY = "0.0";

        viewModel.inputSomething();

        assertEquals(viewModel.isCalcButtonEnabled, true);
    }
}
