package ru.unn.agile.geometry.viewModel;

import ru.unn.agile.geometry.IntersectionDetector;
import ru.unn.agile.geometry.Line;
import ru.unn.agile.geometry.Plane;
import ru.unn.agile.geometry.Point;
import ru.unn.agile.geometry.viewModel.ILogger;
import java.io.PrintWriter;
import java.io.StringWriter;

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
    ILogger logger;

    public  LinePlainIntersection(ILogger logger) {
        this.logger = logger;
    }

    public void inputSomething() {
        logTextFields();
        resultX = resultY = resultZ = "";
        boolean buttonStatus = isInputValid();
        if (buttonStatus != isCalcButtonEnabled) {
            logger.message("BUTTON ENABLE SET: " + buttonStatus);
        }
        isCalcButtonEnabled = buttonStatus;
    }

    public void calc() {
        inputSomething();
        if (!isCalcButtonEnabled) return;

        Point linePoint = parsePoint(linePx, linePy, linePz);
        Point lineDir = parsePoint(lineDirX, lineDirY, lineDirZ);
        Point plainPoint = parsePoint(plainPointX, plainPointY, plainPointZ);
        Point plainOrt = parsePoint(plainOrtX, plainOrtY, plainOrtZ);

        logParsedInput(linePoint, lineDir, plainPoint, plainOrt);

        Line line = new Line(linePoint, lineDir);
        Plane plane = new Plane(plainPoint, plainOrt);

        IntersectionDetector computer = new IntersectionDetector();
        Point res = computer.compute(plane, line);

        if (logger != null) {
            if (res != null) {
                logger.message("RESULT: {" + res.getX() + "," + res.getY() + "," + res.getZ() + "}");
            } else {
                logger.message("RESULT: {NULL}");
            }
        }

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

    private void logParsedInput(Point linePoint, Point lineDir, Point plainPoint, Point plainOrt) {
        if (logger != null) {
            logger.message("PARSED INPUT: lineP{" + linePoint.getX() + "," + linePoint.getY() + "," + linePoint.getZ() +
                    "};lineDir{" + lineDir.getX() + "," + lineDir.getY() + "," + lineDir.getZ() +
                    "};plainP{" + plainPoint.getX() + "," + plainPoint.getY() + "," + plainPoint.getZ() +
                    "};plainOrt{" + plainOrt.getX() + "," + plainOrt.getY() + "," + plainOrt.getZ() + "}");
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
            writeExceptionInDebugLog(e);
            resultX = "Parse error";
            resultY = e.getMessage();
            logger.message("PARSE ERROR: " + e.getMessage());
            return false;
        }
        return true;
    }

    private void writeExceptionInDebugLog(Exception e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        logger.debug(sw.toString());
    }

    private void logTextFields() {
        if (logger != null) {
            logger.message("INPUT: lineP{" + linePx + "," + linePy + "," + linePz +
                    "};lineDir{" + lineDirX + "," + lineDirY + "," + lineDirZ +
                    "};plainP{" + plainPointX + "," + plainPointY + "," + plainPointZ + "" +
                    "};plainOrt{" + plainOrtX + "," + plainOrtY + "," + plainOrtZ + "}");
        }
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
