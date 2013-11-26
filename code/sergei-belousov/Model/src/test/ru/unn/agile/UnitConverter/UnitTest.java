package ru.unn.agile.UnitConverter;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;


public class UnitTest {

    @Test
    public void EqualTwoEqualUnits(){
        Unit unit1 = new Unit("kg", 1.);
        Unit unit2 = new Unit("kg", 1.);
        assertTrue(unit1.isEqual(unit2));
    }

    @Test
    public void NoEqualTwoDifferentUnits(){
        Unit unit1 = new Unit("kg", 1.);
        Unit unit2 = new Unit("g", 1.);
        assertFalse(unit1.isEqual(unit2));
    }
}
