package ru.unn.agile.geometry.viewModel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.unn.agile.geometry.Point;

import static org.junit.Assert.assertEquals;

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
        assertEquals(viewModel.linePx, "");
        assertEquals(viewModel.linePy, "");
        assertEquals(viewModel.linePz, "");
        assertEquals(viewModel.lineDirX, "");
        assertEquals(viewModel.lineDirY, "");
        assertEquals(viewModel.lineDirZ, "");
        assertEquals(viewModel.plainPointX, "");
        assertEquals(viewModel.plainPointY, "");
        assertEquals(viewModel.plainPointZ, "");
        assertEquals(viewModel.plainOrtX, "");
        assertEquals(viewModel.plainOrtY, "");
        assertEquals(viewModel.plainOrtZ, "");
        assertEquals(viewModel.isCalcButtonEnabled, false);
    }

    @Test
    public void whenInputIsIncorrectButtonDisabled() {
        viewModel.linePx = "linePx";
        viewModel.linePy = "linePy";
        viewModel.linePz = "linePz";
        viewModel.lineDirX = "lineDirX";
        viewModel.lineDirY = "lineDirY";
        viewModel.lineDirZ = "lineDirZ";
        viewModel.plainPointX = "plainPointX";
        viewModel.plainPointY = "plainPointY";
        viewModel.plainPointZ = "plainPointZ";
        viewModel.plainOrtX = "plainOrtX";
        viewModel.plainOrtY = "plainOrtY";
        viewModel.plainOrtZ = "plainOrtZ";

        viewModel.inputSomething();

        assertEquals(viewModel.isCalcButtonEnabled, false);
    }

    @Test
    public void whenInputIsCorrectButtonEnabled() {
        viewModel.linePx = "1";
        viewModel.linePy = "1.0";
        viewModel.linePz = "1.0";
        viewModel.lineDirX = "0";
        viewModel.lineDirY = "0.0";
        viewModel.lineDirZ = "1.0";
        viewModel.plainPointX = "0.0";
        viewModel.plainPointY = "0.0";
        viewModel.plainPointZ = "0.0";
        viewModel.plainOrtX = "0.0";
        viewModel.plainOrtY = "0.0";
        viewModel.plainOrtZ = "0.0";

        viewModel.inputSomething();

        assertEquals(viewModel.isCalcButtonEnabled, true);
    }

    @Test
    public void canConvertToPoint() {
        String x = "0";
        String y = "-1.0";
        String z = "2e-1";

        Point result = viewModel.parsePoint(x, y, z);

        assertEquals(result, new Point(0, -1.0, 0.2));
    }

    @Test
    public void whenIntersectionExist() {
        viewModel.linePx = "1";
        viewModel.linePy = "2";
        viewModel.linePz = "3";
        viewModel.lineDirX = "0";
        viewModel.lineDirY = "0";
        viewModel.lineDirZ = "1";
        viewModel.plainPointX = "1";
        viewModel.plainPointY = "2";
        viewModel.plainPointZ = "3";
        viewModel.plainOrtX = "0.0";
        viewModel.plainOrtY = "0.0";
        viewModel.plainOrtZ = "1.0";

        viewModel.inputSomething();
        viewModel.calc();

        Point result = viewModel.parsePoint(viewModel.resultX, viewModel.resultY, viewModel.resultZ);

        assertEquals(result, new Point(1.0, 2.0, 3.0));
    }

    @Test
    public void whenIntersectionNotExist() {
        viewModel.linePx = "0";
        viewModel.linePy = "0";
        viewModel.linePz = "1";
        viewModel.lineDirX = "1";
        viewModel.lineDirY = "1";
        viewModel.lineDirZ = "0";
        viewModel.plainPointX = "0";
        viewModel.plainPointY = "0";
        viewModel.plainPointZ = "0";
        viewModel.plainOrtX = "0.0";
        viewModel.plainOrtY = "0.0";
        viewModel.plainOrtZ = "1.0";

        viewModel.inputSomething();
        viewModel.calc();

        assertEquals(viewModel.resultX, "no intersection");
        assertEquals(viewModel.resultY, "no intersection");
        assertEquals(viewModel.resultZ, "no intersection");
    }
}
