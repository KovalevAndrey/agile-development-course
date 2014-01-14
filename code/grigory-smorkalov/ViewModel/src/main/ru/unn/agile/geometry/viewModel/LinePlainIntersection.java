package ru.unn.agile.geometry.viewModel;

import ru.unn.agile.geometry.IntersectionComputer;
import ru.unn.agile.geometry.Line;
import ru.unn.agile.geometry.Plane;
import ru.unn.agile.geometry.Point;

public class LinePlainIntersection {
    private String linePx = "";
    private String linePy = "";
    private String linePz = "";
    private String lineDirX = "";
    private String lineDirY = "";
    private String lineDirZ = "";
    private String plainPointX = "";
    private String plainPointY = "";
    private String plainPointZ = "";
    private String plainOrtX = "";
    private String plainOrtY = "";
    private String plainOrtZ = "";
    private String resultX = "";
    private String resultY = "";
    private String resultZ = "";
    private boolean isCalcButtonEnabled = false;

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
        Plane plane = new Plane(plainPoint, plainOrt);

        IntersectionComputer computer = new IntersectionComputer();
        Point res = computer.compute(plane, line);

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
            Double.parseDouble(linePz);
            Double.parseDouble(lineDirX);
            Double.parseDouble(lineDirY);
            Double.parseDouble(lineDirZ);
            Double.parseDouble(plainPointX);
            Double.parseDouble(plainPointY);
            Double.parseDouble(plainPointZ);
            Double.parseDouble(plainOrtX);
            Double.parseDouble(plainOrtY);
            Double.parseDouble(plainOrtZ);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public String getLinePx() {
        return linePx;
    }

    public void setLinePx(final String linePx) {
        this.linePx = linePx;
    }

    public String getLinePy() {
        return linePy;
    }

    public void setLinePy(final String linePy) {
        this.linePy = linePy;
    }

    public String getLineDirX() {
        return lineDirX;
    }

    public void setLineDirX(final String lineDirX) {
        this.lineDirX = lineDirX;
    }

    public boolean isCalcButtonEnabled() {
        return isCalcButtonEnabled;
    }

    public String getLineDirY() {
        return lineDirY;
    }

    public void setLineDirY(final String lineDirY) {
        this.lineDirY = lineDirY;
    }

    public String getPlainPointX() {
        return plainPointX;
    }

    public void setPlainPointX(final String plainPointX) {
        this.plainPointX = plainPointX;
    }

    public String getPlainPointY() {
        return plainPointY;
    }

    public void setPlainPointY(final String plainPointY) {
        this.plainPointY = plainPointY;
    }

    public String getPlainOrtX() {
        return plainOrtX;
    }

    public void setPlainOrtX(final String plainOrtX) {
        this.plainOrtX = plainOrtX;
    }

    public String getPlainOrtY() {
        return plainOrtY;
    }

    public void setPlainOrtY(final String plainOrtY) {
        this.plainOrtY = plainOrtY;
    }

    public String getResultX() {
        return resultX;
    }

    public void setResultX(final String resultX) {
        this.resultX = resultX;
    }

    public String getResultY() {
        return resultY;
    }

    public void setResultY(final String resultY) {
        this.resultY = resultY;
    }

    public String getLinePz() {
        return linePz;
    }

    public void setLinePz(final String linePz) {
        this.linePz = linePz;
    }

    public String getLineDirZ() {
        return lineDirZ;
    }

    public void setLineDirZ(final String lineDirZ) {
        this.lineDirZ = lineDirZ;
    }

    public String getPlainPointZ() { return plainPointZ; }

    public void setPlainPointZ(final String lainPointZ) {
        this.plainPointZ = lainPointZ;
    }

    public String getPlainOrtZ() {
        return plainOrtZ;
    }

    public void setPlainOrtZ(final String plainOrtZ) {
        this.plainOrtZ = plainOrtZ;
    }

    public String getResultZ() {
        return resultZ;
    }

    public void setResultZ(final String resultZ) {
        this.resultZ = resultZ;
    }
}
