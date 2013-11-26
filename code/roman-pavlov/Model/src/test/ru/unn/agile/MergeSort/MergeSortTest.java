package ru.unn.agile.MergeSort;

import org.junit.Test;

import static org.junit.Assert.*;

public class MergeSortTest {

    @Test
    public void emptyInputArrayIsNotAllowed() {
        try {
            int[] array = null;
            MergeSort.mergeSort(array);
            fail("Runtime exception was expected");
        } catch (RuntimeException e) {
            assertEquals("Empty input array is not allowed", e.getMessage());
        } catch (Exception e) {
            fail("Unexpected exception: " + e.toString());
        }
    }

    @Test
    public void arrayAfterMergeSortCorrectSort() {
        assertArray(new int[]{1, 2, 3}, new int[]{3, 2, 1});
    }

    @Test
    public void sortArrayCorrectSort() {
        assertArray(new int[]{1, 2, 3}, new int[]{1, 2, 3});
    }

    @Test
    public void arrayWithOneElementCorrectSort() {
        assertArray(new int[]{1}, new int[]{1});
    }

    @Test
    public void arrayWithNegativeElementsCorrectSort() {
        assertArray(new int[]{-3, -2, -1}, new int[]{-1, -2, -3});
    }

    @Test
    public void arrayWithEqualElementsCorrectSort() {
        assertArray(new int[]{1, 1, 1}, new int[]{1, 1, 1});
    }

    @Test
    public void arrayCorrectSort() {
        assertArray(new int[]{-5, -2, 0, 1, 3, 9}, new int[]{1, -5, 0, 9, -2, 3});
    }

    private void assertArray(int[] expectedArray, int[] inputArray) {
        MergeSort.mergeSort(inputArray);
        assertArrayEquals(expectedArray, inputArray);
    }

}
