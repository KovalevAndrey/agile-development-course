package ru.unn.agile.colorConverter.model;

import org.junit.Test;
import static org.junit.Assert.*;

public class XYZSpaceTest {
    @Test
    public void canCreateInstanceWithInitalValues() {
        XYZSpace xyz = new XYZSpace(0, 0, 0);
        assertNotNull(xyz);
    }

    @Test
    public void convertsFromXYZToRGB() throws Exception {
        XYZSpace xyz = new XYZSpace(95, 100, 108);
        RGBColor rgb = xyz.getRgb();

        assert(ColorEqualChecker.almostEqualValues(rgb, 255, 255, 255));
    }
}
