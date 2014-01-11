package ru.unn.agile.geometry.viewModel;

/**
 * Created by geser on 12.01.14.
 */
public class LinePlainIntersection {
    public String lineP1X = "";
    public String lineP1Y = "";
    public String lineP2X = "";
    public String lineP2Y = "";
    public String plainPointX = "";
    public String plainPointY = "";
    public String plainOrtX = "";
    public String plainOrtY = "";
    public String resultX = "";
    public String resultY = "";
    public boolean isCalcButtonEnabled = false;

    public void inputSomething() {
        isCalcButtonEnabled = isInputValid();
    }

    private boolean isInputAvailable() {
        return true;
    }

    private boolean isInputValid() {
        try {
            Double.parseDouble(lineP1X);
            Double.parseDouble(lineP1Y);
            Double.parseDouble(lineP2X);
            Double.parseDouble(lineP2Y);
            Double.parseDouble(plainPointX);
            Double.parseDouble(plainPointY);
            Double.parseDouble(plainOrtX);
            Double.parseDouble(plainOrtY);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
