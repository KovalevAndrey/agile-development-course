package ru.unn.agile.leftistHeap.model;

import org.junit.Test;
import ru.unn.agile.leftistHeap.model.Pair;

import static org.junit.Assert.assertArrayEquals;

public class PairTests {
    private Pair pair;

    @Test
    public void  isDefaultPairCorrect() {
        pair = new Pair();
        assertPairEquals(0, "");
    }

    @Test
    public void isCustomPairCorrect() {
        pair = new Pair(1, "Fine");
        assertPairEquals(1, "Fine");
    }

    @Test (expected = IllegalArgumentException.class)
    public void throwsOnInvalidKey() {
        pair = new Pair(-1, "BadKey");
    }

    @Test (expected = IllegalArgumentException.class)
    public void throwsOnInvalidValue() {
        pair = new Pair(1, null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void throwsOnInvalidPair() {
        pair = new Pair(-1, null);
    }

    private void assertPairEquals(int key, String value) {
        Object[] expected = {key, value};
        Object[] actual = {pair.getKey(), pair.getValue()};
        assertArrayEquals(expected, actual);
    }
}
