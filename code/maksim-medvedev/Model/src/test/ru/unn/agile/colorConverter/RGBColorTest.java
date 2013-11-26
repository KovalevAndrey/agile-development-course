package ru.unn.agile.colorConverter;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created with IntelliJ IDEA.
 * User: maxmedvedev
 * Date: 10/19/13
 * Time: 1:30 AM
 * To change this template use File | Settings | File Templates.
 */
public class RGBColorTest {

    @Test
    public void canCreateInstanceWithInitalValues() throws Exception {
        RGBColor color = new RGBColor(255, 0, 0);
        assertNotNull(color);
    }

    @Test
    public void testGetRGB() throws Exception {
        Color color = new RGBColor(255, 128, 64);
        RGBColor secondColor = color.getRGB();
        assert(ColorEqualChecker.equalColorsByValues((RGBColor)color, secondColor));
    }

    @Test
    public void getRGBThrowsExceptionWhenValuesOutOfRange() throws Exception {
        Color color = new RGBColor(256, 12, -1);
        try {
            color.getRGB();
            fail();
        }
        catch (ColorException e) {
        }
    }

    @Test
    public void throwsExceptionWhenRedValueIsOutOfRange() throws Exception {
        Color color = new RGBColor(256, 0, 0);
        try {
            color.checkValues();
            fail();
        }
        catch (ColorException e) {
        }
    }

    @Test
    public void throwsExceptionWhenGreenValueIsOutOfRange() throws Exception {
        Color color = new RGBColor(0, -15, 0);
        try {
            color.checkValues();
            fail();
        }
        catch (ColorException e) {
        }
    }

    @Test
    public void throwsExceptionWhenBlueValueIsOutOfRange() throws Exception {
        Color color = new RGBColor(0, 0, -1);
        try {
            color.checkValues();
            fail();
        }
        catch (ColorException e) {
        }
    }

    @Test
    public void testToString() throws Exception {
        Color color = new RGBColor(255, 128, 64);
        String output = color.toString();
        assertEquals(output, "RGB(255, 128, 64)");
    }

}
