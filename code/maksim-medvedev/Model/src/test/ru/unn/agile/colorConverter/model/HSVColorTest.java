package ru.unn.agile.colorConverter.model;

import org.junit.Test;
import static org.junit.Assert.*;

public class HSVColorTest {
    @Test
    public void canCreateInstanceWithInitalValues() throws Exception {
        HSVColor color = new HSVColor(20, 75, 100);
        assertNotNull(color);
    }

    @Test
    public void throwsExceptionWhenHueValueIsOutOfRange() throws Exception {
        Color color = new HSVColor(361, 0, 0);
        try {
            color.checkValues();
            fail();
        }
        catch (ColorException e) {
        }
    }

    @Test
    public void throwsExceptionWhenSaturationValueIsOutOfRange() throws Exception {
        Color color = new HSVColor(360, 102, 0);
        try {
            color.checkValues();
            fail();
        }
        catch (ColorException e) {
        }
    }

    @Test
    public void throwsExceptionWhenValueValueIsOutOfRange() throws Exception {
        Color color = new HSVColor(360, 50, -2);
        try {
            color.checkValues();
            fail();
        }
        catch (ColorException e) {
        }
    }

    @Test
    public void convertsBlackColorToRGB() throws Exception {
        Color color = new HSVColor(0, 0, 0);
        RGBColor rgb = color.getRGB();

        assertTrue(ColorEqualChecker.equalColorsByValues(rgb, 0, 0, 0));
    }

    @Test
    public void convertsWhiteColorToRGB() throws Exception {
        Color color = new HSVColor(0, 0, 100);
        RGBColor rgb = color.getRGB();

        assertTrue(ColorEqualChecker.equalColorsByValues(rgb, 255, 255, 255));
    }

    @Test
    public void convertsOrangeColorToRGB() throws Exception {
        Color color = new HSVColor(30, 100, 100);
        RGBColor rgb = color.getRGB();

        assertTrue(ColorEqualChecker.equalColorsByValues(rgb, 255, 127, 0));
    }

    @Test
    public void testToString() throws Exception {
        Color color = new HSVColor(170, 50, 50);
        assertEquals(color.toString(), "HSV(170, 50, 50)");
    }
}
