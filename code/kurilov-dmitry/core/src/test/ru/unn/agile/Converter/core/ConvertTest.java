package ru.unn.agile.Converter.core;

import org.junit.Before;
import org.junit.Test;
import ru.unn.agile.Converter.Converter;

import static org.junit.Assert.*;

public class ConvertTest
{
    private Converter testSet;

    @Before
    public void  SetUp()
    {
        testSet = new Converter();
    }

    @Test
    public void ConvertFromBinaryToDecimalTest()
    {
        String outStr = new String(testSet.ConvertToDecimal("10101",2));
        assertEquals(outStr, "21");
    }

    @Test
    public void ConvertFromOctalToDecimalTest()
    {
        String outStr = new String(testSet.ConvertToDecimal("72",8));
        assertEquals(outStr, "58");
    }

    @Test
    public void ConvertFromHexadecimalToDecimalTest()
    {
        String outStr = new String(testSet.ConvertToDecimal("a",16));
        assertEquals(outStr, "10");
    }

    @Test
    public void ConvertFromDecimalToBinaryTest()
    {
        String outStr = new String(testSet.ConvertFromDecimal("21", 2));
        assertEquals(outStr, "10101");
    }

    @Test
    public void ConvertFromDecimalToOctalTest()
    {
        String outStr = new String(testSet.ConvertFromDecimal("58", 8));
        assertEquals(outStr, "72");
    }

    @Test
    public void ConvertFromDecimalToHexadecimalTest()
    {
        String outStr = new String(testSet.ConvertFromDecimal("10", 16));
        assertEquals(outStr, "a");
    }

    @Test
    public void ConvertFromOneToOtherTest()
    {
        String outStr = new String(testSet.ConvertFromOneToOther("   1111   ",2,16));
        assertEquals(outStr, "f");
    }

    @Test
    public void tryConvertWithUnavailableBaseTest()
    {
        Boolean admissible = testSet.tryConvert("102", 17);
        assertFalse(admissible);
    }

    @Test
    public void tryConvertWithUnavailableBinaryDictionarySymbolsTest()
    {
        Boolean admissible = testSet.tryConvert("102", 2);
        assertFalse(admissible);
    }

    @Test
    public void tryConvertWithUnavailableOctalDictionarySymbolsTest()
    {
        Boolean admissible = testSet.tryConvert("589", 8);
        assertFalse(admissible);
    }

    @Test
    public void tryConvertWithUnavailableDictionarySymbolsTest()
    {
        Boolean admissible = testSet.tryConvert("4fa", 10);
        assertFalse(admissible);
    }

    @Test
    public void tryConvertWithUnavailableHexadecimalBinaryDictionarySymbolsTest()
    {
        Boolean admissible = testSet.tryConvert("Gad", 16);
        assertFalse(admissible);
    }
}
