package ru.unn.agile.converter.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import static ru.unn.agile.converter.model.Unit.*;

public class ConverterOfLengthTest {
    double eps = 1e-5;
    private ConverterOfLength converterOfLength;


    @Before
    public void setUp() {
        converterOfLength = new ConverterOfLength();
    }

    @Test
    public void isCorrectConvertRodToKilometer(){
        assertEquals(0.050292, converterOfLength.convertLength(rod, kilometer, 10), eps);
    }

    @Test
    public void isCorrectConvertMileToChain(){
        assertEquals(800, converterOfLength.convertLength(mile, chain, 10), eps);
    }

    @Test
    public void isCorrectConvertDecimeterToYard(){
        assertEquals(1.093613298, converterOfLength.convertLength(decimeter, yard, 10), eps);
    }

    @Test (expected = IllegalArgumentException.class)
    public void throwsOnOverflowInConvertMileToMillimeter(){
        converterOfLength.convertLength(mile, millimeter, Double.MAX_VALUE);
    }

    @Test (expected = IllegalArgumentException.class)
    public void throwsOnOverflowInConvertKilometerToDecimeter(){
        converterOfLength.convertLength(kilometer, decimeter, Double.MAX_VALUE);
    }

    @Test (expected = IllegalArgumentException.class)
    public void throwsOnOverflowInConvertChainToYard(){
        converterOfLength.convertLength(chain, yard, Double.MAX_VALUE);
    }

    @Test (expected = IllegalArgumentException.class)
    public void throwsOnOverflowExponentInConvert(){
        converterOfLength.convertLength(millimeter, mile, Double.MIN_NORMAL);
    }

    @Test (expected = IllegalArgumentException.class)
    public void throwsOnIllegalInputUnitInConvertLength(){
        converterOfLength.convertLength(null, meter, 10);
    }

    @Test (expected = IllegalArgumentException.class)
    public void throwsOnIllegalOutputUnitInConvertLength(){
        converterOfLength.convertLength(chain, null, 10);
    }

    @Test (expected = IllegalArgumentException.class)
    public void throwsOnNegativeInputValueInConvertLength(){
        converterOfLength.convertLength(meter, kilometer, -10);
    }

}
