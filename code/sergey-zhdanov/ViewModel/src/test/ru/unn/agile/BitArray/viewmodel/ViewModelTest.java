package ru.unn.agile.BitArray.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class ViewModelTest {
    private ViewModel viewModel;
    private ViewModelStateChecker viewModelStateChecker;
    private ILogger logger;

    private void assertState() {
        assertTrue(viewModelStateChecker.equalsToViewModelState(viewModel));
    }
    @Before
    public void setUp() throws Exception {
        logger = mock(ILogger.class);
        viewModel = new ViewModel(logger);
        viewModelStateChecker = new ViewModelStateChecker();
    }

    @After
    public void tearDown() throws Exception {
        viewModel = null;
        viewModelStateChecker = null;
    }

    @Test
    public void emptyArray() {
        //check empty state
        assertState();
    }

    @Test
    public void inputArrayFromBitString() throws Exception {
        final String inputString = "0100100100101001";
        viewModel.inputArrayFromBitString(inputString);
        viewModelStateChecker = new ViewModelStateChecker() {
            {
                curArrayStr = inputString;
                curLenStr = ""+inputString.length();
            }
        };
        assertState();
    }

    @Test
    public void inputArrayFromBitStringExceptional() throws Exception {
        final String inputString = "02a00100100101001";
        viewModel.inputArrayFromBitString(inputString);
        viewModelStateChecker = new ViewModelStateChecker() {
            {
                error = "Input string should contains 0 and 1 digits only";
            }
        };
        assertState();
    }

    @Test
    public void inputArrayFromStringOfInts() throws Exception {
        final String inputString = "  1  , 2";
        final String expectedString = "0000000000000000000000000000000100000000000000000000000000000010";
        viewModel.inputArrayFromStringOfInts(inputString);
        viewModelStateChecker = new ViewModelStateChecker() {
            {
                curArrayStr = expectedString;
                curLenStr = "" + expectedString.length();
            }
        };
        assertState();
    }

    @Test
    public void inputArrayFromNotIntArrayString() throws Exception {
        final String inputString = "  1  . 2";
        viewModel.inputArrayFromStringOfInts(inputString);
        assertEquals(viewModel.getError(), "Can't parse string: For input string: \"" + inputString.trim() + "\"");
    }

    @Test
    public void inputArrayFromStringOfIntsWithNotInt() throws Exception {
        final String inputString = "  1  , 2, b";
        viewModel.inputArrayFromStringOfInts(inputString);
        assertEquals(viewModel.getError(), "Can't parse string: For input string: \"b\"");
    }

    @Test
    public void notAction() throws Exception {
        final String inputString =        "0100100100101001";
        final String reverseInStr = "1011011011010110";
        viewModel.inputArrayFromBitString(inputString);
        viewModel.notAction();
        viewModelStateChecker = new ViewModelStateChecker() {
            {
                curArrayStr = reverseInStr;
                curLenStr = ""+reverseInStr.length();
            }
        };
        assertState();
    }

    @Test
    public void leftShift() throws Exception {
        final String inputString =   "0100100100101001";
        final String expectedString = "1001001001010010";
        viewModel.inputArrayFromBitString(inputString);
        viewModel.leftShift();
        viewModelStateChecker = new ViewModelStateChecker() {
            {
                curArrayStr = expectedString;
                curLenStr = ""+expectedString.length();
            }
        };
        assertState();
    }

    @Test
    public void leftShiftExceptional() throws Exception {
        viewModel.leftShift();
        assertEquals(viewModel.getError(), "Input string should be not null and should have non zero length");
    }

    @Test
    public void rightShift() throws Exception {
        final String inputString =   "0100100100101001";
        final String expectedString = "0010010010010100";
        viewModel.inputArrayFromBitString(inputString);
        viewModel.rightShift();
        viewModelStateChecker = new ViewModelStateChecker() {
            {
                curArrayStr = expectedString;
                curLenStr = ""+expectedString.length();
            }
        };
        assertState();
    }

    @Test
    public void rightShiftExceptional() throws Exception {
        viewModel.rightShift();
        assertEquals(viewModel.getError(), "Input string should be not null and should have non zero length");
    }

    @Test
    public void setZeroToIndexAction() throws Exception {
        final String inputString =   "0100100100101001";
        final String expectedString = "0000100100101001";
        viewModel.inputArrayFromBitString(inputString);
        viewModel.setZeroToIndex("1");
        viewModelStateChecker = new ViewModelStateChecker() {
            {
                curArrayStr = expectedString;
                curLenStr = ""+expectedString.length();
            }
        };
        assertState();
    }

    @Test
    public void setZeroToIndexActionExceptional() throws Exception {
        final String inputString =   "0100100100101001";
        viewModel.inputArrayFromBitString(inputString);
        viewModel.setZeroToIndex("");
        assertTrue(viewModel.getError().length() > 0);
    }

    @Test
    public void setZeroToIndexActionExceptional2() throws Exception {
        final String inputString =   "0100100100101001";
        viewModel.inputArrayFromBitString(inputString);
        viewModel.setZeroToIndex("");
        assertEquals("", inputString, viewModel.getBitStringOfCurrentArray());
    }

    @Test
    public void setZeroToIndexActionExceptional3() throws Exception {
        final String inputString =   "0100100100101001";
        viewModel.inputArrayFromBitString(inputString);
        String stringIndex = "-1";
        viewModel.setZeroToIndex(stringIndex);
        assertEquals(viewModel.getError(), "trying to set with index: " + stringIndex + " when size: " + inputString.length());
    }

    @Test
    public void setZeroToIndexActionExceptional4() throws Exception {
        final String inputString =   "0100100100101001";
        viewModel.inputArrayFromBitString(inputString);
        String stringIndex = "17";
        viewModel.setZeroToIndex(stringIndex);
        assertEquals(viewModel.getError(), "trying to set with index: " + stringIndex + " when size: " + inputString.length());
    }

    @Test
    public void setOneToIndexAction() throws Exception {
        final String inputString =   "0100100100101001";
        final String expectedString = "0110100100101001";
        viewModel.inputArrayFromBitString(inputString);
        viewModel.setOneToIndex("2");
        viewModelStateChecker = new ViewModelStateChecker() {
            {
                curArrayStr = expectedString;
                curLenStr = ""+expectedString.length();
            }
        };
        assertState();
    }

    @Test
    public void markWholeToOutput() throws Exception {
        final String inputString =   "0100100100101001";
        viewModel.inputArrayFromBitString(inputString);
        viewModel.markWholeArrayToOutput();
        viewModelStateChecker = new ViewModelStateChecker() {
            {
                curArrayStr = inputString;
                curLenStr = ""+inputString.length();
                countOutStr = curLenStr;
            }
        };
        assertState();
    }
    @Test
    public void markWholeToOutputExceptional() throws Exception {
        viewModel.markWholeArrayToOutput();
        viewModelStateChecker = new ViewModelStateChecker();
        assertState();
    }

    @Test
    public void outputToStringWholeArray() throws Exception {
        final String inputString =   "0100100100101001";
        viewModel.inputArrayFromBitString(inputString);
        viewModel.setBeginIndexToOutput("0");
        viewModel.setBitsCountToOutput("" + inputString.length());
        viewModel.outputToBitString();
        viewModelStateChecker = new ViewModelStateChecker() {
            {
                curArrayStr = inputString;
                outArrayStr = inputString;
                curLenStr = ""+inputString.length();
                countOutStr = curLenStr;
            }
        };
        assertState();
    }

    @Test
    public void outputToStringSubArray() throws Exception {
        final String inputString =   "0100100100101001";
        viewModel.inputArrayFromBitString(inputString);
        viewModel.setBeginIndexToOutput("1");
        viewModel.setBitsCountToOutput("4");
        final String expectedString = "1001";
        viewModel.outputToBitString();
        viewModelStateChecker = new ViewModelStateChecker() {
            {
                curArrayStr = inputString;
                outArrayStr = expectedString;
                startOutIndStr = "1";
                curLenStr = ""+inputString.length();
                countOutStr = "4";
            }
        };
        assertState();
    }

    @Test
    public void outputToStringOfInts() throws Exception {
        final String inputString =   "1, 2";
        final String expectedString = "0000000000000000000000000000000100000000000000000000000000000010";
        viewModel.inputArrayFromStringOfInts(inputString);
        viewModel.setBeginIndexToOutput("0");
        viewModel.setBitsCountToOutput("" + expectedString.length());
        viewModel.outputToStringOfInts();
        viewModelStateChecker = new ViewModelStateChecker() {
            {
                curArrayStr = expectedString;
                outArrayStr = inputString;
                curLenStr = ""+expectedString.length();
                countOutStr = curLenStr;
            }
        };
        assertState();
    }

    class ViewModelStateChecker {
        public String curArrayStr;
        public String outArrayStr;
        public String startOutIndStr;
        public String countOutStr;
        public String curLenStr;
        public String error;

        public ViewModelStateChecker() {
            curArrayStr = "";
            outArrayStr = "";
            startOutIndStr = "0";
            countOutStr = "0";
            curLenStr = "0";
            error = "";
        }

        public boolean equalsToViewModelState(ViewModel viewModel) {
            if (countOutStr != null ? !countOutStr.equals(viewModel.getBitsCountToOutput()) : viewModel.getBitsCountToOutput() != null) return false;
            if (curArrayStr != null ? !curArrayStr.equals(viewModel.getBitStringOfCurrentArray()) : viewModel.getBitStringOfCurrentArray() != null) return false;
            if (curLenStr != null ? !curLenStr.equals(viewModel.getLengthOfCurrentArray()) : viewModel.getLengthOfCurrentArray() != null) return false;
            if (outArrayStr != null ? !outArrayStr.equals(viewModel.getArrayToOutput()) : viewModel.getArrayToOutput() != null) return false;
            if (error != null ? !error.equals(viewModel.getError()) : viewModel.getError() != null) return false;
            if (startOutIndStr != null ? !startOutIndStr.equals(viewModel.getBeginIndexToOutput()) : viewModel.getBeginIndexToOutput() != null)

                return false;

            return true;
        }

    }
}
