package ru.unn.agile.geometry.viewModel;

import ru.unn.agile.geometry.IntersectionComputer;

public class LinePlainIntersection {
    public String lineP1X = "";
    public String lineP1Y = "";
    public String lineP1Z = "";
    public String lineP2X = "";
    public String lineP2Y = "";
    public String lineP2Z = "";
    public String plainPointX = "";
    public String plainPointY = "";
    public String plainPointZ = "";
    public String plainOrtX = "";
    public String plainOrtY = "";
    public String plainOrtZ = "";
    public String resultX = "";
    public String resultY = "";
    public String resultZ = "";
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
