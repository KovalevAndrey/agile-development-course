package ru.unn.agile.TemperatureConverterTests;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static junit.framework.Assert.*;

import ru.unn.agile.TemperatureConverter.*;
import static ru.unn.agile.TemperatureConverter.AvailableScales.*;

public class TemperatureConverterTests {

    private Temperature t;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() {
        t = new Temperature(36.5, Celsius);
    }

    @Test
    public void canCreateTemperature() {
        assertNotNull(t);
    }

    @Test
    public void temperatureScaleCannotBeNull() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Scale cannot be null");
        Temperature t = new Temperature(36.6, null);
    }

    @Test
    public void canGetPrettyFormat() {
        assertEquals("24.3 Ro", Romer.toString(24.3));
    }

    @Test
    public void canConvertTemperature() {
        Temperature t = new Temperature(28.5, Romer);
        Temperature result = t.scaleTo(Celsius);
        assertEquals(Celsius, result.getScale());
        assertEquals(40.0, result.getCurrent());
    }

    @Test
    public void temperatureConvertScaleCannotBeNull() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Scale cannot be null");
        t.scaleTo(null);
    }
}
