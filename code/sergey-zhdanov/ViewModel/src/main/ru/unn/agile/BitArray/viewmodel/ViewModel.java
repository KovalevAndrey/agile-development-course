package ru.unn.agile.BitArray.viewmodel;

import com.szhdanov.main.BitArray;

public class ViewModel {

    private BitArray bitArray;
    //field to bind
    private String bitStringOfCurrentArray;
    private String arrayToOutput;
    private String beginIndexToOutput;
    private String bitsCountToOutput;
    private String lengthOfCurrentArray;
    private String error;

    public ViewModel() {
        this.bitArray = new BitArray();
        updateState();
    }
    private void updateState() {
        this.bitStringOfCurrentArray = bitArray.toBitString();
        this.arrayToOutput = "";
        this.beginIndexToOutput = "0";
        this.bitsCountToOutput = "0";
        this.lengthOfCurrentArray = "" + bitArray.length();
        this.error = "";
    }
    //actions
    public void inputArrayFromBitString(String str) {
        try {
            bitArray = BitArray.fromString(str);
            updateState();
        } catch (Exception ex) {
            error = ex.getMessage();
        }
    }

    private int[] intArrayFromStr(String strInts) {
        if(strInts.length() == 0) {
            throw new IllegalArgumentException("Please input some string");
        }
        String[] strIntegers = strInts.split(",");
        int[] intArray = new int[strIntegers.length];
        for(int i = 0; i < intArray.length; ++i) {
            intArray[i] = Integer.parseInt(strIntegers[i].trim());
        }
        return intArray;
    }

    public  void inputArrayFromStringOfInts(String str) {
        try {
            int[] ints = intArrayFromStr(str);
            bitArray = BitArray.fromArray(ints);
            updateState();
        } catch (Exception ex) {
            error = ex.getMessage();
        }
    }

    public void notAction() {
        try {
            bitArray = bitArray.not();
            updateState();
        } catch (Exception ex) {
            error = ex.getMessage();
        }
    }

    public void leftShift() {
        try {
            bitArray = bitArray.lshft(1);
            updateState();
        } catch (Exception ex) {
            error = ex.getMessage();
        }
    }

    public void rightShift() {
        try {
            bitArray = bitArray.rshft(1);
            updateState();
        } catch (Exception ex) {
            error = ex.getMessage();
        }
    }

    public void setZeroToIndex(String index) {
        try {
            int intIndex = Integer.parseInt(index);
            bitArray.set(intIndex, 0);
            updateState();
        } catch (Exception ex) {
            error = ex.getMessage();
        }
    }

    public void setOneToIndex(String index) {
        try {
            int intIndex = Integer.parseInt(index);
            bitArray.set(intIndex, 1);
            updateState();
        } catch (Exception ex) {
            error = ex.getMessage();
        }
    }

    public void markWholeArrayToOutput() {
        try {
            updateState();
            beginIndexToOutput = "0";
            bitsCountToOutput = lengthOfCurrentArray;
        } catch (Exception ex) {
            error = ex.getMessage();
        }
    }

    public void outputToBitString() {
        try {
            int startInd = Integer.parseInt(beginIndexToOutput);
            int count = Integer.parseInt(bitsCountToOutput);
            BitArray subArray = bitArray.subArray(startInd, count);
            arrayToOutput = subArray.toBitString();
        } catch (Exception ex) {
            error = ex.getMessage();
        }
    }

    private String intArrayToString(int[] ints) {
        if(ints.length == 0) {
            return "";
        } else if (ints.length == 1) {
            return "" + ints[0];
        }
        StringBuilder strIntegersBuilder = new StringBuilder();
        for(int i = 0; i < ints.length - 1; ++i) {
            strIntegersBuilder.append(ints[i]);
            strIntegersBuilder.append(", ");
        }
        strIntegersBuilder.append(ints[ints.length - 1]);
        return strIntegersBuilder.toString();
    }

    public void outputToStringOfInts() {
        try {
            int startInd = Integer.parseInt(beginIndexToOutput);
            int count = Integer.parseInt(bitsCountToOutput);
            BitArray subArray = bitArray.subArray(startInd, count);
            int[] ints = subArray.toIntArray();
            arrayToOutput = intArrayToString(ints);
        } catch (Exception ex) {
            error = ex.getMessage();
        }
    }

    public String getBitStringOfCurrentArray() {
        return bitStringOfCurrentArray;
    }

    public void setBitStringOfCurrentArray(String bitStringOfCurrentArray) {
        this.bitStringOfCurrentArray = bitStringOfCurrentArray;
    }

    public String getArrayToOutput() {
        return arrayToOutput;
    }

    public void setArrayToOutput(String arrayToOutput) {
        this.arrayToOutput = arrayToOutput;
    }

    public String getBeginIndexToOutput() {
        return beginIndexToOutput;
    }

    public void setBeginIndexToOutput(String beginIndexToOutput) {
        this.beginIndexToOutput = beginIndexToOutput;
    }

    public String getBitsCountToOutput() {
        return bitsCountToOutput;
    }

    public void setBitsCountToOutput(String bitsCountToOutput) {
        this.bitsCountToOutput = bitsCountToOutput;
    }

    public String getLengthOfCurrentArray() {
        return lengthOfCurrentArray;
    }

    public void setLengthOfCurrentArray(String lengthOfCurrentArray) {
        this.lengthOfCurrentArray = lengthOfCurrentArray;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
