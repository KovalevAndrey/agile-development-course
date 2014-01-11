package ru.unn.agile.geometry.viewModel;

import ru.unn.agile.geometry.IntersectionComputer;
import ru.unn.agile.geometry.Line;
import ru.unn.agile.geometry.Plain;
import ru.unn.agile.geometry.Point;

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

    public void calc() {
        inputSomething();
        if (!isCalcButtonEnabled) return;

        Point lineP1 = parsePoint(lineP1X, lineP1Y, lineP1Z);
        Point lineP2 = parsePoint(lineP2X, lineP2Y, lineP2Z);
        Point plainPoint = parsePoint(plainPointX, plainPointY, plainPointZ);
        Point plainOrt = parsePoint(plainOrtX, plainOrtY, plainOrtZ);

        Line line = new Line(lineP1, lineP2);
        Plain plain = new Plain(plainPoint, plainOrt);

        IntersectionComputer computer = new IntersectionComputer();
        Point res = computer.compute(plain, line);

        resultX = "" + res.getX();
        resultY = "" + res.getY();
        resultZ = "" + res.getZ();
    }

    public Point parsePoint(String x, String y, String z) {
        Point res;
        try {
            res = new Point(Double.parseDouble(x), Double.parseDouble(y), Double.parseDouble(z));
        }
        catch (Exception e) {
            return null;
        }
        return res;
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
