

import com.szhdanov.main.BitArray;

import java.util.ArrayList;

public class ViewModel {

    private BitArray model;
    //field to bind
    private String curArrayStr;
    private String outArrayStr;
    private String startOutIndStr;
    private String countOutStr;
    private String curLenStr;
    private String error;

    public ViewModel() {
        this.model = new BitArray();
        updateState();
    }
    private void updateState() {
        this.curArrayStr = model.toBitString();
        this.outArrayStr = "";
        this.startOutIndStr = "0";
        this.countOutStr = "0";
        this.curLenStr = "" + model.length();
        this.error = "";
    }
    //actions
    public void strInputAction(String str) {
        try {
            model = BitArray.fromString(str);
            updateState();
        } catch (Exception ex) {
            error = ex.getMessage();
        }
    }

    private int[] intArrayFromStr(String strInts) {
        if(strInts.length() == 0) {
            throw new IllegalArgumentException("Please input some string");
        }
        String[] elems = strInts.split(",");
        int[] res = new int[elems.length];
        for(int i = 0; i < res.length; ++i) {
            res[i] = Integer.parseInt(elems[i].trim());
        }
        return res;
    }

    public  void intArrayInputAction(String str) {
        try {
            int[] ints = intArrayFromStr(str);
            model = BitArray.fromArray(ints);
            updateState();
        } catch (Exception ex) {
            error = ex.getMessage();
        }
    }

    public void notAction() {
        try {
            model = model.not();
            updateState();
        } catch (Exception ex) {
            error = ex.getMessage();
        }
    }

    public void  lshtAction() {
        try {
            model = model.lshft(1);
            updateState();
        } catch (Exception ex) {
            error = ex.getMessage();
        }
    }

    public void rshtAction() {
        try {
            model = model.rshft(1);
            updateState();
        } catch (Exception ex) {
            error = ex.getMessage();
        }
    }

    public void setZeroToIndexAction(String index) {
        try {
            int intIndex = Integer.parseInt(index);
            model.set(intIndex, 0);
            updateState();
        } catch (Exception ex) {
            error = ex.getMessage();
        }
    }

    public void setOneToIndexAction(String index) {
        try {
            int intIndex = Integer.parseInt(index);
            model.set(intIndex, 1);
            updateState();
        } catch (Exception ex) {
            error = ex.getMessage();
        }
    }

    public void wholeToOutAction() {
        try {
            updateState();
            startOutIndStr = "0";
            countOutStr = curLenStr;
        } catch (Exception ex) {
            error = ex.getMessage();
        }
    }

    public void outToStrAction() {
        try {
            updateState();
            outArrayStr = curArrayStr;
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
        StringBuilder tmpRes = new StringBuilder();
        for(int i = 0; i < ints.length - 1; ++i) {
            tmpRes.append(ints[i]);
            tmpRes.append(", ");
        }
        tmpRes.append(ints[ints.length - 1]);
        return tmpRes.toString();
    }

    public void outToIntsAction() {
        try {
            updateState();
            int[] ints = model.toIntArray();
            outArrayStr = intArrayToString(ints);
        } catch (Exception ex) {
            error = ex.getMessage();
        }
    }

    public String getCurArrayStr() {
        return curArrayStr;
    }

    public void setCurArrayStr(String curArrayStr) {
        this.curArrayStr = curArrayStr;
    }

    public String getOutArrayStr() {
        return outArrayStr;
    }

    public void setOutArrayStr(String outArrayStr) {
        this.outArrayStr = outArrayStr;
    }

    public String getStartOutIndStr() {
        return startOutIndStr;
    }

    public void setStartOutIndStr(String startOutIndStr) {
        this.startOutIndStr = startOutIndStr;
    }

    public String getCountOutStr() {
        return countOutStr;
    }

    public void setCountOutStr(String countOutStr) {
        this.countOutStr = countOutStr;
    }

    public String getCurLenStr() {
        return curLenStr;
    }

    public void setCurLenStr(String curLenStr) {
        this.curLenStr = curLenStr;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
