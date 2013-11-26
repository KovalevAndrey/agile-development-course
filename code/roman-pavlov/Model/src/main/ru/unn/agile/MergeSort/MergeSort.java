package ru.unn.agile.MergeSort;

public class MergeSort {
    public static void mergeSort(int[] array) {
        if (array == null) {
            throw new RuntimeException("Empty input array is not allowed");
        }
        mergeSort(array, 0, array.length - 1);
    }

    private static void mergeSort(int[] array, int leftBorder, int rightBorder) {
        if (leftBorder >= rightBorder) return;
        int middleElement = (leftBorder + rightBorder) / 2;
        mergeSort(array, leftBorder, middleElement);
        mergeSort(array, middleElement + 1, rightBorder);
        merge(array, leftBorder, middleElement, rightBorder);
    }

    private static void merge(int[] array, int leftBorder, int middleElement, int rightBorder) {
        if (middleElement + 1 > rightBorder) return;
        int[] tempArray = new int[array.length];
        for (int i = leftBorder; i != middleElement + 1; i++) {
            tempArray[i] = array[i];
        }

        for (int i = middleElement + 1; i != rightBorder + 1; i++) {
            tempArray[i] = array[rightBorder + middleElement + 1 - i];
        }

        int k = leftBorder;
        int j = rightBorder;
        for (int i = leftBorder; i != rightBorder + 1; i++) {
            if (tempArray[k] <= tempArray[j]) array[i] = tempArray[k++];
            else array[i] = tempArray[j--];
        }
    }
}
