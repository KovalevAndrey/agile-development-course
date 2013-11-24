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
    public void ConvertFromBinaryToDecimalTest() throws Exception
    {
        String outStr = new String(testSet.ConvertToDecimal("10101",2));
        assertEquals(outStr, "21");
    }

    @Test
    public void ConvertFromBinaryToDecimalWithUnavailableInputTest()
    {
        try
        {
            String outStr = new String(testSet.ConvertToDecimal("10201",2));
            fail();
        }
        catch (Exception e)
        {
            e.getMessage();
        }
    }

    @Test
    public void ConvertFromOctalToDecimalTest() throws Exception
    {
        String outStr = new String(testSet.ConvertToDecimal("72",8));
        assertEquals(outStr, "58");
    }

    @Test
    public void ConvertFromOctalToDecimalWithUnavailableInputTest() throws Exception
    {
        try
        {
            String outStr = new String(testSet.ConvertToDecimal("92",8));
            fail();
        }
        catch (Exception e)
        {
            e.getMessage();
        }
    }

    @Test
    public void ConvertFromHexadecimalToDecimalTest() throws Exception
    {
        String outStr = new String(testSet.ConvertToDecimal("a",16));
        assertEquals(outStr, "10");
    }

    @Test
    public void ConvertFromHexadecimalToDecimalWithUnavailableInputTest() throws Exception
    {
        try
        {
            String outStr = new String(testSet.ConvertToDecimal("g",16));
        }
        catch (Exception e)
        {
            e.getMessage();
        }
    }

    @Test
    public void ConvertFromDecimalToBinaryTest() throws Exception
    {
        String outStr = new String(testSet.ConvertFromDecimal("21", 2));
        assertEquals(outStr, "10101");
    }

    @Test
    public void ConvertFromDecimalToBinaryWithUnavailableInputTest() throws Exception
    {
        try
        {
            String outStr = new String(testSet.ConvertFromDecimal("f1", 2));
        }
        catch (Exception e)
        {
            e.getMessage();
        }
    }

    @Test
    public void ConvertFromDecimalToOctalTest() throws Exception
    {
        String outStr = new String(testSet.ConvertFromDecimal("58", 8));
        assertEquals(outStr, "72");
    }

    @Test
    public void ConvertFromDecimalToOctalWithUnavailableInputTest() throws Exception
    {
        try
        {
            String outStr = new String(testSet.ConvertFromDecimal("f8", 8));
        }
        catch (Exception e)
        {
            e.getMessage();
        }

    }

    @Test
    public void ConvertFromDecimalToHexadecimalTest() throws Exception
    {
        String outStr = new String(testSet.ConvertFromDecimal("10", 16));
        assertEquals(outStr, "a");
    }

    @Test
    public void ConvertFromDecimalToHexadecimalWithUnavailableInputTest() throws Exception
    {
        try
        {
            String outStr = new String(testSet.ConvertFromDecimal("f0", 16));
        }
        catch (Exception e)
        {
            e.getMessage();
        }
    }

    @Test
    public void ConvertFromOneToOtherTest() throws Exception
    {
        String outStr = new String(testSet.ConvertFromOneToOther("   1111   ",2,16));
        assertEquals(outStr, "f");
    }

    @Test
    public void tryConvertWithUnavailableBaseTest()
    {
        try
        {
            testSet.tryConvert("102", 17);
            fail();
        }
        catch (Exception e)
        {
            e.getMessage();
        }
    }

    @Test
    public void tryConvertWithUnavailableBinaryDictionarySymbolsTest()
    {
        try
        {
            testSet.tryConvert("102", 2);
            fail();
        }
        catch (Exception e)
        {
            e.getMessage();
        }
    }

    @Test
    public void tryConvertWithUnavailableOctalDictionarySymbolsTest()
    {
        try
        {
            testSet.tryConvert("589", 8);
            fail();
        }
        catch (Exception e)
        {
            e.getMessage();
        }
    }

    @Test
    public void tryConvertWithUnavailableDictionarySymbolsTest()
    {
        try
        {
            testSet.tryConvert("4fa", 10);
            fail();
        }
        catch (Exception e)
        {
            e.getMessage();
        }
    }

    @Test
    public void tryConvertWithUnavailableHexadecimalBinaryDictionarySymbolsTest()
    {
        try
        {
            testSet.tryConvert("Gad", 16);
            fail();
        }
        catch (Exception e)
        {
            e.getMessage();
        }
    }
}
