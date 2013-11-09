package ru.unn.agile.MergeSort;

public class MergeSortViewModel {
    String statusText = "";
    String arrayText = "";
    String resultText = "";

    public void processSort() {
        if (isValidInput()) {
            int[] array = parseInput();
            MergeSort.mergeSort(array);
            writeResult(array);
            statusText = "Sort success!!!";
        } else {
            statusText = "Invalid input!!!";
            resultText = "";
        }
    }

    private void writeResult(int[] array) {
        resultText = "";
        for (int i = 0; i < array.length; i++) {
            resultText += String.valueOf(array[i]);
            if (i < array.length - 1) resultText += " ";
        }
    }

    private int[] parseInput() {
        String[] arrStrings = arrayText.split("[ ]");
        int arrayLength = 0;
        for (int i = 0; i < arrStrings.length; i++) {
            if (arrStrings[i].equals(""))
                continue;
            arrayLength++;
        }
        int[] result = new int[arrayLength];
        int index = 0;
        for (int i = 0; i < arrStrings.length; i++) {
            if (arrStrings[i].equals(""))
                continue;
            result[index] = Integer.parseInt(arrStrings[i]);
            index++;
        }
        return result;
    }

    private boolean isValidInput() {
        String validSymbols = "0123456789 ";
        for (int i = 0; i < arrayText.length(); i++) {
            if (validSymbols.indexOf(arrayText.charAt(i)) < 0)
                return false;
        }
        return true;
    }
}
