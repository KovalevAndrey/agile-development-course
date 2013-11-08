package ru.unn.agile.colorConverter;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created with IntelliJ IDEA.
 * User: maxmedvedev
 * Date: 10/19/13
 * Time: 1:50 AM
 * To change this template use File | Settings | File Templates.
 */
public class LABColorTest {
    @Test
    public void canCreateInstanceWithInitalValues() throws Exception {
        Color color = new LABColor(0, -128, 128);
        assertNotNull(color);
    }

    @Test
    public void throwsExceptionWhenLValueIsOutOfRange() throws Exception {
        Color color = new LABColor(101, 0, 0);
        try {
            color.checkValues();
            fail();
        }
        catch (ColorException e) {
        }
    }

    @Test
    public void throwsExceptionWhenAValueIsOutOfRange() throws Exception {
        Color color = new LABColor(0, 129, 0);
        try {
            color.checkValues();
            fail();
        }
        catch (ColorException e) {
        }
    }

    @Test
    public void throwsExceptionWhenBValueIsOutOfRange() throws Exception {
        Color color = new LABColor(0, 0, -129);
        try {
            color.checkValues();
            fail();
        }
        catch (ColorException e) {
        }
    }

    @Test
    public void convertsBlackColorToRGB() throws Exception {
        Color color = new LABColor(0, 0, 0);
        RGBColor rgb = color.getRGB();

        assertTrue(ColorEqualChecker.almostEqualValues(rgb, 0, 0, 0));
    }

    @Test
    public void convertsWhiteColorToRGB() throws Exception {
        Color color = new LABColor(100, 0, 0);
        RGBColor rgb = color.getRGB();

        assertTrue(ColorEqualChecker.almostEqualValues(rgb, 255, 255, 255));
    }

    @Test
    public void convertsOrangeColorToRGB() throws Exception {
        Color color = new LABColor(67, 43, 74);
        RGBColor rgb = color.getRGB();

        assertTrue(ColorEqualChecker.almostEqualValues(rgb, 255, 127, 0));
    }

    @Test
    public void testToString() throws Exception {
        Color color = new LABColor(0, 1, 2);
        assertEquals(color.toString(), "LAB(0, 1, 2)");
    }
}
