package ru.unn.agile.UnitConverter;

import org.junit.Test;
import ru.unn.agile.UnitConverter.UnitKey;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class UnitKeyTest {

    @Test
    public void equalTwoEqualKeys() {
        UnitKey unitKey1 = new UnitKey("kg", "g");
        UnitKey unitKey2 = new UnitKey("kg", "g");
        assertEquals(unitKey1, unitKey2);
    }

    @Test
    public void noEqualTwoDifferentKeys() {
        UnitKey unitKey1 = new UnitKey("g", "g");
        UnitKey unitKey2 = new UnitKey("kg", "g");
        assertFalse(unitKey1.equals(unitKey2));
    }

    @Test
    public void equalsHashCodeTwoEqualKeys() {
        UnitKey unitKey1 = new UnitKey("kg", "g");
        UnitKey unitKey2 = new UnitKey("kg", "g");
        assertEquals(unitKey1.hashCode(), unitKey2.hashCode());
    }

    @Test
    public void noEqualsHashCodeTwoDifferentKeys() {
        UnitKey unitKey1 = new UnitKey("g", "g");
        UnitKey unitKey2 = new UnitKey("kg", "g");
        assertFalse(unitKey1.hashCode() == unitKey2.hashCode());
    }

    @Test
    public void keyReverseEqualReverseKey() {
        UnitKey unitKey = new UnitKey("kg", "g");
        assertEquals(unitKey.reverse(), new UnitKey("g", "kg"));
    }

    @Test
    public void keyToStringEqualStringFromHyphenToString() {
        UnitKey unitKey = new UnitKey("kg", "g");
        assertEquals("kg-g", unitKey.toString());
    }

}
