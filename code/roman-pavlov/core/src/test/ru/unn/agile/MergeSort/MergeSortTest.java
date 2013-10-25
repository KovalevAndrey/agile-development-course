package ru.unn.agile.MergeSort;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class MergeSortTest {

    @Test
    public void emptyInputArrayIsNotAllowed(){
        try {
            int[] array=null;
            MergeSort.mergeSort(array);
            fail("Runtime exception was expected");
        }
        catch (RuntimeException e){
            assertEquals("Empty input array is not allowed",e.getMessage());
        }
        catch (Exception e){
            fail("Unexpected exception: " + e.toString());
        }
    }

    @Test
    public void arrayAfterMergeSortCorrectSort(){
        int[] array=new int[]{3,2,1};
        MergeSort.mergeSort(array);
        assertArrayEquals(new int[]{1,2,3}, array);
    }

    @Test
    public void sortArrayCorrectSort(){
        int[] array=new int[]{1,2,3};
        MergeSort.mergeSort(array);
        assertArrayEquals(new int[]{1,2,3}, array);
    }

    @Test
    public void arrayWithOneElementCorrectSort(){
        int[] array=new int[]{1};
        MergeSort.mergeSort(array);
        assertArrayEquals(new int[]{1}, array);
    }
}
