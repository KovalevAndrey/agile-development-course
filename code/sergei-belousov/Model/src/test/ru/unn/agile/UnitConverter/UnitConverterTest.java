package ru.unn.agile.UnitConverter;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UnitConverterTest {
    final double epsilon = 1e-12;
    private UnitConverter weightConverter;

    @Before
    public void setUp() {
        weightConverter = new UnitConverter();
        weightConverter.addPair("kg", "g", 1000.);
        weightConverter.addPair("kg", "ct", 5000.);
        weightConverter.addPair("kg", "lb", 2.205);
    }

    @Test
    public void InOneKilogramThousandGrams() {
        assertConvertReturns(1000., "kg", "g", 1.);
    }

    @Test
    public void InOneKilogramFiveThousandCarats() {
        assertConvertReturns(5000., "kg", "ct", 1.);
    }

    @Test
    public void InOneGramOneThousandthKilogram() {
        assertConvertReturns(0.001, "g", "kg", 1.);
    }

    @Test
    public void InOneKilogramTwoPointTwoHundredAndFivePound() {
        assertConvertReturns(2.205, "kg", "lb", 1.);
    }

    @Test
    public void convertFromKilogramToGram(){
        Unit kilograms = new Unit("kg", 1.);
        try{
            Unit grams = weightConverter.convert(kilograms, "g");
            assertTrue(grams.isEqual(new Unit("g", 1000.)));
        } catch(UnitConvertTableException e){
            System.out.println(e.getMessage());
            fail();
        }
    }

    @Test
    public void canClearUnitConverter() {
        weightConverter.clear();
        assertTrue(weightConverter.isEmpty());
    }

    private void assertConvertReturns(double expected, String from, String to, double value)
    {
        try {
            String formatString = from + "-" + to;
            assertEquals(expected, weightConverter.convert(formatString, value).getValue(), epsilon);
        } catch (UnitConvertTableException e) {
            System.out.println(e.getMessage());
            fail("catch exception");
        }
    }
}
