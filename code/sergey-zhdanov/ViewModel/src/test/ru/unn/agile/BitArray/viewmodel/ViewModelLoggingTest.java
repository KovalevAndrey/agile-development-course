package ru.unn.agile.BitArray.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class ViewModelLoggingTest {
    private ViewModel viewModel;
    private ILogger logger;

    @Before
    public void setUp() throws Exception {
        logger = mock(ILogger.class);
        viewModel = new ViewModel(logger);
    }

    @After
    public void tearDown() throws Exception {
        viewModel = null;
    }

    @Test
    public void createLog() {
        //check empty state
        verify(logger).log(ViewModelLoggingMessages.VIEW_MODEL_CREATED);
    }

    @Test
    public void successfulInputFromBitString() throws Exception {
        String bitString = "00010101010";
        viewModel.inputArrayFromBitString(bitString);
        verify(logger).log(ViewModelLoggingMessages.SUCCESSFUL_INPUT_OF_ARRAY_FROM_STRING +bitString);
    }
    @Test
    public void inputFromBadBitString() throws Exception {
        String bitString = "000a10101010";
        viewModel.inputArrayFromBitString(bitString);
        verify(logger).log(ViewModelLoggingMessages.UNSUCCESSFUL_ATTEMPT_TO_INPUT_ARRAY_FROM_BAD_BIT_STARING +bitString);
    }

    @Test
    public void inputArrayFromStringOfInts() throws Exception {
        String bitString = "1, 2, 3";
        viewModel.inputArrayFromStringOfInts(bitString);
        verify(logger).log(ViewModelLoggingMessages.INPUT_ARRAY_FROM_STRING_OF_INTS_SUCCESS +bitString);
    }
    @Test
    public void inputArrayFromStringOfIntsErr() throws Exception {
        String bitString = "1, 2 . 9";
        viewModel.inputArrayFromStringOfInts(bitString);
        verify(logger).log(ViewModelLoggingMessages.INPUT_ARRAY_FROM_STRING_OF_INTS_FAIL +bitString);
    }

    @Test
    public void notAction() throws Exception {
        String bitString = "00010101010";
        viewModel.inputArrayFromBitString(bitString);
        viewModel.notAction();
        verify(logger).log(ViewModelLoggingMessages.NOT_ACTION_SUCCESS);
    }

    @Test
    public void leftShift() throws Exception {
        String bitString = "00010101010";
        viewModel.inputArrayFromBitString(bitString);
        viewModel.leftShift();
        verify(logger).log(ViewModelLoggingMessages.LEFT_SHIFT_SUCCESS);
    }
    @Test
    public void leftShiftErr() throws Exception {
        viewModel.leftShift();
        verify(logger).log(ViewModelLoggingMessages.LEFT_SHIFT_FAIL);
    }

    @Test
    public void rightShift() throws Exception {
        String bitString = "00010101010";
        viewModel.inputArrayFromBitString(bitString);
        viewModel.rightShift();
        verify(logger).log(ViewModelLoggingMessages.RIGHT_SHIFT_SUCCESS);
    }
    @Test
    public void rightShiftErr() throws Exception {
        viewModel.rightShift();
        verify(logger).log(ViewModelLoggingMessages.RIGHT_SHIFT_FAIL);
    }

    @Test
    public void setZeroToIndex() throws Exception {
        String bitString = "00010101010";
        viewModel.inputArrayFromBitString(bitString);
        String index = "1";
        viewModel.setZeroToIndex(index);
        verify(logger).log(ViewModelLoggingMessages.SET_ZERO_TO_INDEX_SUCCESS_INDEX +index);
    }
    @Test
    public void setZeroToIndexErr() throws Exception {
        String index = "1";
        viewModel.setZeroToIndex(index);
        verify(logger).log(ViewModelLoggingMessages.SET_ZERO_TO_INDEX_FAIL_INDEX +index);
    }

    private boolean isEqv = false;
    @Test
    public void setOneToIndex() throws Exception {
        String bitString = "00010101010";
        viewModel.inputArrayFromBitString(bitString);
        String index = "0";
        viewModel.setOneToIndex(index);
        verify(logger).log(ViewModelLoggingMessages.SET_ONE_TO_INDEX_SUCCESS_INDEX + index);
    }
    @Test
    public void setOneToIndexErr() throws Exception {
        String index = "1";
        viewModel.setOneToIndex(index);
        verify(logger).log(ViewModelLoggingMessages.SET_ONE_TO_INDEX_FAIL_INDEX +index);
    }

    @Test
    public void markWholeArrayToOutput() throws Exception {
        String bitString = "00010101010";
        viewModel.inputArrayFromBitString(bitString);
        viewModel.markWholeArrayToOutput();
        verify(logger).log(ViewModelLoggingMessages.MARK_WHOLE_ARRAY_TO_OUTPUT_SUCCESS);
    }

    @Test
    public void outputToBitString() throws Exception {
        String bitString = "00010101010";
        viewModel.inputArrayFromBitString(bitString);
        viewModel.markWholeArrayToOutput();
        viewModel.outputToBitString();
        verify(logger).log(ViewModelLoggingMessages.OUTPUT_TO_BIT_STRING_SUCCESS +bitString);
    }
    @Test
    public void outputToBitStringErr() throws Exception {
        viewModel.outputToBitString();
        verify(logger).log(ViewModelLoggingMessages.OUTPUT_TO_BIT_STRING_FAIL);
    }

    @Test
    public void outputToStringOfInts() throws Exception {
        String intString = "1, 3";
        viewModel.inputArrayFromStringOfInts(intString);
        viewModel.markWholeArrayToOutput();
        viewModel.outputToStringOfInts();
        verify(logger).log(ViewModelLoggingMessages.OUTPUT_TO_STRING_OF_INTS_SUCCESS +intString);
    }

    @Test
    public void outputToStringOfIntsErr() throws Exception {
        viewModel.outputToStringOfInts();
        verify(logger).log(ViewModelLoggingMessages.OUTPUT_TO_STRING_OF_INTS_FAIL);
    }

    @Test
    public void setBeginIndexToOutput() throws Exception {
        String index = "3";
        viewModel.setBeginIndexToOutput(index);
        verify(logger).log(ViewModelLoggingMessages.SET_BEGIN_INDEX_TO_OUTPUT +index);
    }

    @Test
    public void setBitsCountToOutput() throws Exception {
        String count = "3";
        viewModel.setBitsCountToOutput(count);
        verify(logger).log(ViewModelLoggingMessages.SET_BITS_COUNT_TO_OUTPUT +count);
    }
}
