package ru.unn.agile.areaConverterTests;

import ru.unn.agile.areaConverter.*;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class AreaTests {
    private Area area;
    private final double delta = 1e-7;

    @Test
    public void canCreateAreaInstance() {
        area = new Area(1.0, "Metre");
        assertNotNull(area);
    }

    @Test
    public void canSetNegativeArea() {
        try {
            area = new Area(-1.0, "Metre");
            fail();
        }
        catch (Exception e) {
            assertEquals("Negative measure", e.getMessage());
        }
    }

    @Test
    public void canSetWrongScale() {
        try {
            area = new Area(1.0, "some wrong scale");
            fail();
        }
        catch (Exception e) {
            assertEquals("Wrong or empty scale", e.getMessage());
        }
    }

    @Test
    public void canPerformToSiConvertation() {
        area = new Area(1.0, "Hectare");
        assertEquals(10000.0, area.toSI(), delta);
    }

    @Test
    public void canPerformFromSiConvertation() {
        area = new Area(12.3, "Metre");
        assertEquals(12.3 / 0.83612736, area.fromSI("Yard"), delta);
    }

}
