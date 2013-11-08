package ru.unn.agile.interpolationSearch;

public class InterpolationSearch {
    public static final int NOT_FOUND = -1;

    public int Search(int[] sortedArray, int key){
        if (sortedArray.length == 0) {
            return NOT_FOUND;
        }

        int middleIndex;
        int lowIndex = 0;
        int highIndex = sortedArray.length - 1;

        while (sortedArray[lowIndex] < key && sortedArray[highIndex] > key) {
            middleIndex = lowIndex + ((key - sortedArray[lowIndex]) * (highIndex - lowIndex)) /
                    (sortedArray[highIndex] - sortedArray[lowIndex]);

            if (sortedArray[middleIndex] < key)
                lowIndex = middleIndex + 1;
            else if (sortedArray[middleIndex] > key)
                highIndex = middleIndex - 1;
            else
                return middleIndex;
        }

        if (sortedArray[lowIndex] == key)
            return lowIndex;
        else if (sortedArray[highIndex] == key)
            return highIndex;
        else
            return NOT_FOUND;
    }
}
