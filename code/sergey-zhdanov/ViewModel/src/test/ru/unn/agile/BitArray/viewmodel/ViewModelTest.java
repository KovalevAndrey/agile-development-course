package ru.unn.agile.BitArray.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ViewModelTest {
    private ViewModel viewModel;
    private ViewModelState viewModelState;
    private void assertState() {
        assertTrue(viewModelState.equalsToViewModel(viewModel));
    }
    @Before
    public void setUp() throws Exception {
        viewModel = new ViewModel();
    }

    @After
    public void tearDown() throws Exception {
        viewModel = null;
        viewModelState = null;
    }
    @Test
    public void init() {
        viewModelState = new ViewModelState() {
            {
                curArrayStr = "";
                outArrayStr = "";
                startOutIndStr = "0";
                countOutStr = "0";
                curLenStr = "0";
                error = "";
            }
        };
        assertState();
    }

    @Test
    public void strInputAction() throws Exception {
        final String inStr = "0100100100101001";
        viewModel.strInputAction(inStr);
        viewModelState = new ViewModelState() {
            {
                curArrayStr = inStr;
                outArrayStr = "";
                startOutIndStr = "0";
                countOutStr = "0";
                curLenStr = ""+inStr.length();
                error = "";
            }
        };
        assertState();
    }

    @Test
    public void strInputActionEx() throws Exception {
        final String inStr = "02a00100100101001";
        viewModel.strInputAction(inStr);
        viewModelState = new ViewModelState() {
            {
                curArrayStr = "";
                outArrayStr = "";
                startOutIndStr = "0";
                countOutStr = "0";
                curLenStr = "0";
                error = "Input string must contains 0 and 1 digits only";
            }
        };
        assertState();
    }

    @Test
    public void intArrayInputAction() throws Exception {
        final String inStr = "  1  , 2";
        final String mustStr = "0000000000000000000000000000000100000000000000000000000000000010";
        viewModel.intArrayInputAction(inStr);
        viewModelState = new ViewModelState() {
            {
                curArrayStr = mustStr;
                outArrayStr = "";
                startOutIndStr = "0";
                countOutStr = "0";
                curLenStr = "" + mustStr.length();
                error = "";
            }
        };
        assertState();
    }

    @Test
    public void intArrayInputActionEx1() throws Exception {
        final String inStr = "  1  . 2";
        viewModel.intArrayInputAction(inStr);
        assertTrue(viewModel.getError().length() > 0);
    }

    @Test
    public void intArrayInputActionEx2() throws Exception {
        final String inStr = "  1  , 2, b";
        viewModel.intArrayInputAction(inStr);
        assertTrue(viewModel.getError().length() > 0);
    }

    @Test
    public void notAction() throws Exception {
        final String inStr =        "0100100100101001";
        final String reverseInStr = "1011011011010110";
        viewModel.strInputAction(inStr);
        viewModel.notAction();
        viewModelState = new ViewModelState() {
            {
                curArrayStr = reverseInStr;
                outArrayStr = "";
                startOutIndStr = "0";
                countOutStr = "0";
                curLenStr = ""+reverseInStr.length();
                error = "";
            }
        };
        assertState();
    }

    @Test
    public void notActionEx() throws Exception {
        viewModel.notAction();
        viewModelState = new ViewModelState() {
            {
                curArrayStr = "";
                outArrayStr = "";
                startOutIndStr = "0";
                countOutStr = "0";
                curLenStr = "0";
                error = "";
            }
        };
        assertState();
    }

    @Test
    public void lshtAction() throws Exception {
        final String inStr =   "0100100100101001";
        final String mustStr = "1001001001010010";
        viewModel.strInputAction(inStr);
        viewModel.lshtAction();
        viewModelState = new ViewModelState() {
            {
                curArrayStr = mustStr;
                outArrayStr = "";
                startOutIndStr = "0";
                countOutStr = "0";
                curLenStr = ""+mustStr.length();
                error = "";
            }
        };
        assertState();
    }

    @Test
    public void lshtActionEx() throws Exception {
        viewModel.lshtAction();
        assertTrue(viewModel.getError().length() > 0);
    }

    @Test
    public void rshtAction() throws Exception {
        final String inStr =   "0100100100101001";
        final String mustStr = "0010010010010100";
        viewModel.strInputAction(inStr);
        viewModel.rshtAction();
        viewModelState = new ViewModelState() {
            {
                curArrayStr = mustStr;
                outArrayStr = "";
                startOutIndStr = "0";
                countOutStr = "0";
                curLenStr = ""+mustStr.length();
                error = "";
            }
        };
        assertState();
    }

    @Test
    public void rshtActionEx() throws Exception {
        viewModel.lshtAction();
        assertTrue(viewModel.getError().length() > 0);
    }

    @Test
    public void setZeroToIndexAction() throws Exception {
        final String inStr =   "0100100100101001";
        final String mustStr = "0000100100101001";
        viewModel.strInputAction(inStr);
        viewModel.setZeroToIndexAction("1");
        viewModelState = new ViewModelState() {
            {
                curArrayStr = mustStr;
                outArrayStr = "";
                startOutIndStr = "0";
                countOutStr = "0";
                curLenStr = ""+mustStr.length();
                error = "";
            }
        };
        assertState();
    }

    @Test
    public void setZeroToIndexActionEx1() throws Exception {
        final String inStr =   "0100100100101001";
        viewModel.strInputAction(inStr);
        viewModel.setZeroToIndexAction("");
        assertTrue(viewModel.getError().length() > 0);
    }

    @Test
    public void setZeroToIndexActionEx1_2() throws Exception {
        final String inStr =   "0100100100101001";
        viewModel.strInputAction(inStr);
        viewModel.setZeroToIndexAction("");
        assertEquals("", inStr, viewModel.getCurArrayStr());
    }

    @Test
    public void setZeroToIndexActionEx2() throws Exception {
        final String inStr =   "0100100100101001";
        viewModel.strInputAction(inStr);
        viewModel.setZeroToIndexAction("-1");
        assertTrue(viewModel.getError().length() > 0);
    }

    @Test
    public void setZeroToIndexActionEx3() throws Exception {
        final String inStr =   "0100100100101001";
        viewModel.strInputAction(inStr);
        viewModel.setZeroToIndexAction("17");
        assertTrue(viewModel.getError().length() > 0);
    }

    @Test
    public void setOneToIndexAction() throws Exception {
        final String inStr =   "0100100100101001";
        final String mustStr = "0110100100101001";
        viewModel.strInputAction(inStr);
        viewModel.setOneToIndexAction("2");
        viewModelState = new ViewModelState() {
            {
                curArrayStr = mustStr;
                outArrayStr = "";
                startOutIndStr = "0";
                countOutStr = "0";
                curLenStr = ""+mustStr.length();
                error = "";
            }
        };
        assertState();
    }

    @Test
    public void wholeToOutAction() throws Exception {
        final String inStr =   "0100100100101001";
        viewModel.strInputAction(inStr);
        viewModel.wholeToOutAction();
        viewModelState = new ViewModelState() {
            {
                curArrayStr = inStr;
                outArrayStr = "";
                startOutIndStr = "0";
                curLenStr = ""+inStr.length();
                countOutStr = curLenStr;
                error = "";
            }
        };
        assertState();
    }
    @Test
    public void wholeToOutActionEx() throws Exception {
        viewModel.wholeToOutAction();
        viewModelState = new ViewModelState() {
            {
                curArrayStr = "";
                outArrayStr = "";
                startOutIndStr = "0";
                curLenStr = "0";
                countOutStr = "0";
                error = "";
            }
        };
        assertState();
    }

    @Test
    public void outToStrAction() throws Exception {
        final String inStr =   "0100100100101001";
        viewModel.strInputAction(inStr);
        viewModel.setStartOutIndStr("0");
        viewModel.setCountOutStr(""+inStr.length());
        viewModel.outToStrAction();
        viewModelState = new ViewModelState() {
            {
                curArrayStr = inStr;
                outArrayStr = inStr;
                startOutIndStr = "0";
                curLenStr = ""+inStr.length();
                countOutStr = curLenStr;
                error = "";
            }
        };
        assertState();
    }

    @Test
    public void outToStrActionWithArgs() throws Exception {
        final String inStr =   "0100100100101001";
        viewModel.strInputAction(inStr);
        viewModel.setStartOutIndStr("1");
        viewModel.setCountOutStr("4");
        final String mustStr = "1001";
        viewModel.outToStrAction();
        viewModelState = new ViewModelState() {
            {
                curArrayStr = inStr;
                outArrayStr = mustStr;
                startOutIndStr = "1";
                curLenStr = ""+inStr.length();
                countOutStr = "4";
                error = "";
            }
        };
        assertState();
    }

    @Test
    public void outToIntsAction() throws Exception {
        final String inStr =   "1, 2";
        final String mustStr = "0000000000000000000000000000000100000000000000000000000000000010";
        viewModel.intArrayInputAction(inStr);
        viewModel.setStartOutIndStr("0");
        viewModel.setCountOutStr(""+mustStr.length());
        viewModel.outToIntsAction();
        viewModelState = new ViewModelState() {
            {
                curArrayStr = mustStr;
                outArrayStr = inStr;
                startOutIndStr = "0";
                curLenStr = ""+mustStr.length();
                countOutStr = curLenStr;
                error = "";
            }
        };
        assertState();
    }

    class ViewModelState {
        public String curArrayStr;
        public String outArrayStr;
        public String startOutIndStr;
        public String countOutStr;
        public String curLenStr;
        public String error;

        public boolean equalsToViewModel(ViewModel viewModel) {
            if (countOutStr != null ? !countOutStr.equals(viewModel.getCountOutStr()) : viewModel.getCountOutStr() != null) return false;
            if (curArrayStr != null ? !curArrayStr.equals(viewModel.getCurArrayStr()) : viewModel.getCurArrayStr() != null) return false;
            if (curLenStr != null ? !curLenStr.equals(viewModel.getCurLenStr()) : viewModel.getCurLenStr() != null) return false;
            if (outArrayStr != null ? !outArrayStr.equals(viewModel.getOutArrayStr()) : viewModel.getOutArrayStr() != null) return false;
            if (error != null ? !error.equals(viewModel.getError()) : viewModel.getError() != null) return false;
            if (startOutIndStr != null ? !startOutIndStr.equals(viewModel.getStartOutIndStr()) : viewModel.getStartOutIndStr() != null)

                return false;

            return true;
        }

    }
}
