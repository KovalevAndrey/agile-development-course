package ru.unn.agile.BitArray.viewmodel;

import com.szhdanov.main.BitArray;

import java.util.List;

public class ViewModel {

    private ILogger logger;
    private BitArray bitArray;
    //field to bind
    private String bitStringOfCurrentArray;
    private String arrayToOutput;
    private String beginIndexToOutput;
    private String bitsCountToOutput;
    private String lengthOfCurrentArray;
    private String error;

    public ViewModel(ILogger logger) {
        this.logger = logger;
        this.bitArray = new BitArray();
        updateState();
        this.logger.log(ViewModelLoggingMessages.VIEW_MODEL_CREATED);
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
            logger.log(ViewModelLoggingMessages.SUCCESSFUL_INPUT_OF_ARRAY_FROM_STRING +str);
        } catch (Exception ex) {
            setError(ex.getMessage());
            logger.log(ViewModelLoggingMessages.UNSUCCESSFUL_ATTEMPT_TO_INPUT_ARRAY_FROM_BAD_BIT_STARING +str);
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
            logger.log(ViewModelLoggingMessages.INPUT_ARRAY_FROM_STRING_OF_INTS_SUCCESS +str);
        } catch (Exception ex) {
            setError("Can't parse string: " + ex.getMessage());
            logger.log(ViewModelLoggingMessages.INPUT_ARRAY_FROM_STRING_OF_INTS_FAIL +str);
        }
    }

    public void notAction() {
        try {
            bitArray = bitArray.not();
            updateState();
            logger.log(ViewModelLoggingMessages.NOT_ACTION_SUCCESS);
        } catch (Exception ex) {
            setError(ex.getMessage());
            logger.log(ViewModelLoggingMessages.NOT_ACTION_FAIL);
        }
    }

    public void leftShift() {
        try {
            bitArray = bitArray.lshft(1);
            updateState();
            logger.log(ViewModelLoggingMessages.LEFT_SHIFT_SUCCESS);
        } catch (Exception ex) {
            setError(ex.getMessage());
            logger.log(ViewModelLoggingMessages.LEFT_SHIFT_FAIL);
        }
    }

    public void rightShift() {
        try {
            bitArray = bitArray.rshft(1);
            updateState();
            logger.log(ViewModelLoggingMessages.RIGHT_SHIFT_SUCCESS);
        } catch (Exception ex) {
            setError(ex.getMessage());
            logger.log(ViewModelLoggingMessages.RIGHT_SHIFT_FAIL);
        }
    }

    public void setZeroToIndex(String index) {
        try {
            int intIndex = Integer.parseInt(index);
            bitArray.set(intIndex, 0);
            updateState();
            logger.log(ViewModelLoggingMessages.SET_ZERO_TO_INDEX_SUCCESS_INDEX +index);
        } catch (Exception ex) {
            setError(ex.getMessage());
            logger.log(ViewModelLoggingMessages.SET_ZERO_TO_INDEX_FAIL_INDEX +index);
        }
    }

    public void setOneToIndex(String index) {
        try {
            int intIndex = Integer.parseInt(index);
            bitArray.set(intIndex, 1);
            updateState();
            logger.log(ViewModelLoggingMessages.SET_ONE_TO_INDEX_SUCCESS_INDEX +index);
        } catch (Exception ex) {
            setError(ex.getMessage());
            logger.log(ViewModelLoggingMessages.SET_ONE_TO_INDEX_FAIL_INDEX +index);
        }
    }

    public void markWholeArrayToOutput() {
        try {
            updateState();
            beginIndexToOutput = "0";
            bitsCountToOutput = lengthOfCurrentArray;
            logger.log(ViewModelLoggingMessages.MARK_WHOLE_ARRAY_TO_OUTPUT_SUCCESS);
        } catch (Exception ex) {
            setError(ex.getMessage());
            logger.log(ViewModelLoggingMessages.MARK_WHOLE_ARRAY_TO_OUTPUT_FAIL);
        }
    }

    public void outputToBitString() {
        try {
            int startInd = Integer.parseInt(beginIndexToOutput);
            int count = Integer.parseInt(bitsCountToOutput);
            BitArray subArray = bitArray.subArray(startInd, count);
            arrayToOutput = subArray.toBitString();
            logger.log(ViewModelLoggingMessages.OUTPUT_TO_BIT_STRING_SUCCESS +arrayToOutput);
        } catch (Exception ex) {
            setError(ex.getMessage());
            logger.log(ViewModelLoggingMessages.OUTPUT_TO_BIT_STRING_FAIL);
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
            logger.log(ViewModelLoggingMessages.OUTPUT_TO_STRING_OF_INTS_SUCCESS +arrayToOutput);
        } catch (Exception ex) {
            setError(ex.getMessage());
            logger.log(ViewModelLoggingMessages.OUTPUT_TO_STRING_OF_INTS_FAIL);
        }
    }

    public String getBitStringOfCurrentArray() {
        return bitStringOfCurrentArray;
    }

    public String getArrayToOutput() {
        return arrayToOutput;
    }

    public String getBeginIndexToOutput() {
        return beginIndexToOutput;
    }

    public void setBeginIndexToOutput(String beginIndexToOutput) {
        this.beginIndexToOutput = beginIndexToOutput;
        logger.log(ViewModelLoggingMessages.SET_BEGIN_INDEX_TO_OUTPUT +beginIndexToOutput);
    }

    public String getBitsCountToOutput() {
        return bitsCountToOutput;
    }

    public void setBitsCountToOutput(String bitsCountToOutput) {
        this.bitsCountToOutput = bitsCountToOutput;
        logger.log(ViewModelLoggingMessages.SET_BITS_COUNT_TO_OUTPUT +bitsCountToOutput);
    }

    public String getLengthOfCurrentArray() {
        return lengthOfCurrentArray;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
        logger.log(ViewModelLoggingMessages.SET_ERROR +error);
    }

    public List<String> getLog() {
        return logger.getLogs();
    }
}
