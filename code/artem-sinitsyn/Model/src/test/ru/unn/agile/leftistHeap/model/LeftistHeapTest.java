package ru.unn.agile.leftistHeap.model;

import org.junit.Before;
import org.junit.Test;
import ru.unn.agile.leftistHeap.model.LeftistHeap;
import ru.unn.agile.leftistHeap.model.Pair;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class LeftistHeapTest {
    private Pair defaultData;
    private LeftistHeap emptyHeap;
    private LeftistHeap defaultOneElementHeap;
    private LeftistHeap anotherOneElementHeap;

    @Before
    public void setUp() {
        defaultData = new Pair(0, "zero");
        emptyHeap = new LeftistHeap();
        defaultOneElementHeap = new LeftistHeap(defaultData);
        anotherOneElementHeap = new LeftistHeap(new Pair(1, "one"));
    }

    @Test
    public void newHeapIsEmpty(){
        assertEquals(true, emptyHeap.isEmpty());
    }

    @Test
    public void newHeapHasNoNodes(){
        assertEquals(0, emptyHeap.getCount());
    }

    @Test
    public void newOneElementHeapIsNotEmpty() {
        assertEquals(false, defaultOneElementHeap.isEmpty());
    }

    @Test
    public void newOneElementHeapHasOneNode() {
        assertEquals(1, defaultOneElementHeap.getCount());
    }

    @Test
    public void isRootSetFreeCorrect() {
        defaultOneElementHeap.setFree();
        assertEquals(null, defaultOneElementHeap.getRoot());
    }

    @Test
    public void isCountSetFreeCorrect() {
        defaultOneElementHeap.setFree();
        assertEquals(0, defaultOneElementHeap.getCount());
    }

    @Test
    public void isHeapAddCorrect() {
        anotherOneElementHeap.add(defaultData);
        assertPairDataEquals(defaultData, anotherOneElementHeap.getMin());
    }

    @Test
    public void isHeapCountIncreasedAfterAdd() {
        emptyHeap.add(defaultData);
        assertEquals(1, emptyHeap.getCount()) ;
    }

    @Test (expected = RuntimeException.class)
    public void throwsOnGetMinFromEmptyHeap() {
        Pair min = emptyHeap.getMin();
    }

    @Test
    public void isGetMinSuccessful() {
        Pair min = defaultOneElementHeap.getMin();
        assertPairDataEquals(defaultData, min);
    }

    @Test (expected = RuntimeException.class)
    public void throwsOnDeleteMinFromEmptyHeap() {
        emptyHeap.deleteMin();
    }

    @Test
    public void isDeleteMinSuccessful() {
        defaultOneElementHeap.deleteMin();
        assertEquals(null, defaultOneElementHeap.getRoot());
    }

    @Test
    public void isHeapCountDecreasedAfterDeleteMin() {
        defaultOneElementHeap.deleteMin();
        assertEquals(0, defaultOneElementHeap.getCount());
    }

    @Test (expected = IllegalArgumentException.class)
    public void throwsOnMergeWithItself() {
        emptyHeap.merge(emptyHeap);
    }

    @Test
    public void isMergeEmptyHeapWithNotEmptyOneSuccessful() {
        emptyHeap.merge(defaultOneElementHeap);
        assertPairDataEquals(defaultData, emptyHeap.getMin());
    }

    @Test
    public void isMergeNotEmptyHeapWithEmptyOneSuccessful() {
        defaultOneElementHeap.merge(emptyHeap);
        assertPairDataEquals(defaultData, defaultOneElementHeap.getMin());
    }

    @Test
    public void isMergeTwoEmptyHeapsSuccessful() {
        LeftistHeap newEmptyHeap = new LeftistHeap();
        emptyHeap.merge(newEmptyHeap);
        assertEquals(null, emptyHeap.getRoot());
    }

    @Test
    public void isMergedHeapCountCorrect() {
        defaultOneElementHeap.merge(anotherOneElementHeap);
        assertEquals(2, defaultOneElementHeap.getCount());
    }

    @Test
    public void isMergedHeapRootCorrect() {
        anotherOneElementHeap.merge(defaultOneElementHeap);
        assertPairDataEquals(defaultData, anotherOneElementHeap.getMin());
    }

    @Test
    public void mergingHeapBecomesEmpty() {
        defaultOneElementHeap.merge(anotherOneElementHeap);
        assertEquals(null, anotherOneElementHeap.getRoot());
    }

    private void assertPairDataEquals(Pair expectedPair, Pair actualPair) {
        Object[] expected = {expectedPair.getKey(), expectedPair.getValue()};
        Object[] actual = {actualPair.getKey(), actualPair.getValue()};
        assertArrayEquals(expected, actual);
    }
}
