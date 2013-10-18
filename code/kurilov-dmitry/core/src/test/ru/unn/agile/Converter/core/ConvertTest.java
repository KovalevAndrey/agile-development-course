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
    public void ConvertToDecimalTest()
    {
        Converter testSet = new Converter();
        String outStr = new String(testSet.ConvertToDecimal("a",16));
        assertEquals(outStr, "10");
    }

    @Test
    public void ConvertFromDecimalTest()
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
        Boolean admissible = testSet.OpportunityOfConvert("10x",2);
        assertFalse(admissible);
    }
}
