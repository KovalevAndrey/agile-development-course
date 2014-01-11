package ru.unn.agile.geometry.viewModel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.unn.agile.geometry.viewModel.LinePlainIntersection;

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

    }
}
