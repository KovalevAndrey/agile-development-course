package ru.unn.agile.geometry.viewModel;

import ru.unn.agile.geometry.IntersectionComputer;
import ru.unn.agile.geometry.Line;
import ru.unn.agile.geometry.Plain;
import ru.unn.agile.geometry.Point;

public class LinePlainIntersection {
    public String linePx = "";
    public String linePy = "";
    public String linePz = "";
    public String lineDirX = "";
    public String lineDirY = "";
    public String lineDirZ = "";
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

        Point linePoint = parsePoint(linePx, linePy, linePz);
        Point lineDir = parsePoint(lineDirX, lineDirY, lineDirZ);
        Point plainPoint = parsePoint(plainPointX, plainPointY, plainPointZ);
        Point plainOrt = parsePoint(plainOrtX, plainOrtY, plainOrtZ);

        Line line = new Line(linePoint, lineDir);
        Plain plain = new Plain(plainPoint, plainOrt);

        IntersectionComputer computer = new IntersectionComputer();
        Point res = computer.compute(plain, line);

        if (res != null) {
            resultX = "" + res.getX();
            resultY = "" + res.getY();
            resultZ = "" + res.getZ();
        } else {
            resultX = "no intersection";
            resultY = "no intersection";
            resultZ = "no intersection";
        }
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
            Double.parseDouble(linePx);
            Double.parseDouble(linePy);
            Double.parseDouble(lineDirX);
            Double.parseDouble(lineDirY);
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
