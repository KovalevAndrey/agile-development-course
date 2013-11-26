package ru.unn.agile.UnitConverter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class WeightConverterTest {
    final double epsilon = 1e-12;
    private WeightConverter weightConverter;

    @Before
    public void setUp() {
        weightConverter = new WeightConverter();
    }

    @Test
    public void canUsingDefaultTable() {
        try {
            Unit unit = weightConverter.convert(new Unit("kg", 1.), "g");
            assertEquals(1000., unit.getValue(), epsilon);
            assertEquals("g", unit.getType());
        } catch (UnitConvertTableException e) {
            fail(e.getMessage());
        }
    }
}
