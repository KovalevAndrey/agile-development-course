package ru.unn.agile.Converter.core;

import org.junit.Test;
import ru.unn.agile.Converter.Converter;

import static org.junit.Assert.*;

public class ConvertTest
{
	// @Test
	// public void failingTest()
	// {
	// 	fail();
	// }
    @Test
    public void ConvertFromBinaryToDecimalTest()
    {
        Converter testSet = new Converter();
        String outStr = new String(testSet.ConvertToDecimal("10101",2));
        assertEquals(outStr, "21");
    }

    @Test
    public void ConvertFromOctalToDecimalTest()
    {
        Converter testSet = new Converter();
        String outStr = new String(testSet.ConvertToDecimal("72",8));
        assertEquals(outStr, "58");
    }

    @Test
    public void ConvertFromHexadecimalToDecimalTest()
    {
        Converter testSet = new Converter();
        String outStr = new String(testSet.ConvertToDecimal("a",16));
        assertEquals(outStr, "10");
    }

    @Test
    public void ConvertFromDecimalToBinaryTest()
    {
        Converter testSet = new Converter();
        String outStr = new String(testSet.ConvertFromDecimal("21", 2));
        assertEquals(outStr, "10101");
    }

    @Test
    public void ConvertFromDecimalToOctalTest()
    {
        Converter testSet = new Converter();
        String outStr = new String(testSet.ConvertFromDecimal("58", 8));
        assertEquals(outStr, "72");
    }

    @Test
    public void ConvertFromDecimalToHexadecimalTest()
    {
        Converter testSet = new Converter();
        String outStr = new String(testSet.ConvertFromDecimal("10", 16));
        assertEquals(outStr, "a");
    }

    @Test
    public void ConvertFromOneToOtherTest()
    {
        Converter testSet = new Converter();
        String outStr = new String(testSet.ConvertFromOneToOther("   1111   ",2,16));
        assertEquals(outStr, "f");
    }

    @Test
    public void OpportunityOfConvertTest()
    {
        Converter testSet = new Converter();
        Boolean admissible = testSet.OpportunityOfConvert("102",2);
        assertFalse(admissible);
    }
}
