package ru.unn.agile.MergeSort;

public class MergeSortViewModel {
    String statusText = "";
    String arrayText = "";
    String resultText = "";
    public ILogger logger;

    MergeSortViewModel(ILogger logger){
        if (logger == null)
            throw new IllegalArgumentException("Logger is null");
        this.logger = logger;
    }

    public void processSort() {
        logger.log("Processing array: " + arrayText);
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
        logger.log("Result array: " + resultText);
    }

    private int[] parseInput() {
        logger.log("Array string parsing");
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
        logger.log("Array string validating");
        String validSymbols = "0123456789 ";
        for (int i = 0; i < arrayText.length(); i++) {
            if (validSymbols.indexOf(arrayText.charAt(i)) < 0)
            {
                logger.log("Array string is invalid");
                return false;
            }
        }
        logger.log("Array string is valid");
        return true;
    }
}
