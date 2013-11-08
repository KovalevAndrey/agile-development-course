package ru.unn.agile.areaConverterTests;

import ru.unn.agile.areaConverter.*;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class AreaConverterTests {

    private final double delta = 1e-7;

    @Test
    public void canPerfromConvert() {
        double value = AreaConverter.convert(10.0, "Hectare", "Yard");
        assertEquals(10.0 * 10000 / 0.83612736, value, delta);
    }

}


