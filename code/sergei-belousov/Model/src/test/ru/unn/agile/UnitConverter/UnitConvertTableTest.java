package ru.unn.agile.UnitConverter;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class UnitConvertTableTest {
    private final double epsilon = 1e-12;

    @Test
    public void emptyTableReallyEmpty() {
        UnitConvertTable unitConvertTable = new UnitConvertTable();
        assertTrue(unitConvertTable.isEmpty());
    }

    @Test
    public void notEmptyTableReallyNotEmpty() {
        UnitConvertTable unitConvertTable = new UnitConvertTable();
        unitConvertTable.addPair(new UnitKey("kg", "g"), 1000.);
        assertFalse(unitConvertTable.isEmpty());
    }

    @Test
    public void ifAddKeyThenCanGetKey() {
        UnitConvertTable unitConvertTable = new UnitConvertTable();
        unitConvertTable.addPair(new UnitKey("kg", "g"), 1000.);
        try {
            assertEquals(1000., unitConvertTable.getFactor(new UnitKey("kg", "g")), epsilon);
        } catch(UnitConvertTableException e){
            System.out.println("key not found.");
        }
    }

    @Test
    public void correctAutoAddReversePair() {
        UnitConvertTable unitConvertTable = new UnitConvertTable();
        unitConvertTable.addPair(new UnitKey("kg", "g"), 1000.);
        try {
            assertEquals(0.001, unitConvertTable.getFactor(new UnitKey("g", "kg")), epsilon);
        } catch(UnitConvertTableException e){
            System.out.println("key not found.");
        }
    }

    @Test
    public void correctExceptionForUnknownKey() {
        UnitConvertTable unitConvertTable = new UnitConvertTable();
        try {
            unitConvertTable.getFactor(new UnitKey("g", "kg"));
        } catch(UnitConvertTableException e) {
            assertTrue(e.getMessage().contains("key not found."));
        }
    }

    @Test
    public void canClearTable() {
        UnitConvertTable unitConvertTable = new UnitConvertTable();
        unitConvertTable.addPair(new UnitKey("kg", "g"), 1000.);
        unitConvertTable.clear();
        assertTrue(unitConvertTable.isEmpty());
    }

}
