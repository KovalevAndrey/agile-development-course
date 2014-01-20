package ru.unn.agile.geometry.viewModel;

import ru.unn.agile.geometry.IntersectionDetector;
import ru.unn.agile.geometry.Line;
import ru.unn.agile.geometry.Plane;
import ru.unn.agile.geometry.Point;
import ru.unn.agile.geometry.viewModel.ILogger;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import java.lang.StringBuilder;

public class LinePlaneIntersection {
    private String linePx = "";
    private String linePy = "";
    private String linePz = "";
    private String lineDirX = "";
    private String lineDirY = "";
    private String lineDirZ = "";
    private String planePointX = "";
    private String planePointY = "";
    private String planePointZ = "";
    private String planeOrtX = "";
    private String planeOrtY = "";
    private String planeOrtZ = "";
    private String resultX = "";
    private String resultY = "";
    private String resultZ = "";
    private boolean isCalcButtonEnabled = false;
    ILogger logger;

    public LinePlaneIntersection(ILogger logger) {
        this.logger = logger;
    }

    public void inputSomething() {
        logTextFields();
        resultX = resultY = resultZ = "";
        boolean buttonStatus = isInputValid();
        if (buttonStatus != isCalcButtonEnabled) {
            logMessage("BUTTON ENABLE SET: " + buttonStatus);
        }
        isCalcButtonEnabled = buttonStatus;
    }

    public void calc() {
        inputSomething();
        if (!isCalcButtonEnabled) return;

        Point linePoint = parsePoint(linePx, linePy, linePz);
        Point lineDir = parsePoint(lineDirX, lineDirY, lineDirZ);
        Point planePoint = parsePoint(planePointX, planePointY, planePointZ);
        Point planeOrt = parsePoint(planeOrtX, planeOrtY, planeOrtZ);

        logParsedInput(linePoint, lineDir, planePoint, planeOrt);

        Line line = new Line(linePoint, lineDir);
        Plane plane = new Plane(planePoint, planeOrt);

        IntersectionDetector computer = new IntersectionDetector();
        Point res = computer.compute(plane, line);

        if (res != null) {
            logMessage("RESULT: {" + res.getX() + "," + res.getY() + "," + res.getZ() + "}");
        } else {
            logMessage("RESULT: {NULL}");
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

    private void logParsedInput(Point linePoint, Point lineDir, Point planePoint, Point planeOrt) {
        logMessage("PARSED INPUT: lineP{" + linePoint.getX() + "," + linePoint.getY() + "," + linePoint.getZ() +
                "};lineDir{" + lineDir.getX() + "," + lineDir.getY() + "," + lineDir.getZ() +
                "};planeP{" + planePoint.getX() + "," + planePoint.getY() + "," + planePoint.getZ() +
                "};planeOrt{" + planeOrt.getX() + "," + planeOrt.getY() + "," + planeOrt.getZ() + "}");
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
            Double.parseDouble(planePointX);
            Double.parseDouble(planePointY);
            Double.parseDouble(planePointZ);
            Double.parseDouble(planeOrtX);
            Double.parseDouble(planeOrtY);
            Double.parseDouble(planeOrtZ);
        } catch (Exception e) {
            writeExceptionInDebugLog(e);
            resultX = "Parse error";
            resultY = e.getMessage();
            logMessage("PARSE ERROR: " + e.getMessage());
            return false;
        }
        return true;
    }

    private void writeExceptionInDebugLog(Exception e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        if (logger != null) {
            logger.debug(sw.toString());
        }
    }

    private void logTextFields() {
        logMessage("INPUT: lineP{" + linePx + "," + linePy + "," + linePz +
                "};lineDir{" + lineDirX + "," + lineDirY + "," + lineDirZ +
                "};planeP{" + planePointX + "," + planePointY + "," + planePointZ + "" +
                "};planeOrt{" + planeOrtX + "," + planeOrtY + "," + planeOrtZ + "}");
    }

    private void logMessage(String message) {
        if (logger != null) {
            logger.message(message);
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

    public String getPlanePointX() {
        return planePointX;
    }

    public void setPlanePointX(final String planePointX) {
        this.planePointX = planePointX;
    }

    public String getPlanePointY() {
        return planePointY;
    }

    public void setPlanePointY(final String planePointY) {
        this.planePointY = planePointY;
    }

    public String getPlaneOrtX() {
        return planeOrtX;
    }

    public void setPlaneOrtX(final String planeOrtX) {
        this.planeOrtX = planeOrtX;
    }

    public String getPlaneOrtY() {
        return planeOrtY;
    }

    public void setPlaneOrtY(final String planeOrtY) {
        this.planeOrtY = planeOrtY;
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

    public String getPlanePointZ() { return planePointZ; }

    public void setPlanePointZ(final String lainPointZ) {
        this.planePointZ = lainPointZ;
    }

    public String getPlaneOrtZ() {
        return planeOrtZ;
    }

    public void setPlaneOrtZ(final String planeOrtZ) {
        this.planeOrtZ = planeOrtZ;
    }

    public String getResultZ() {
        return resultZ;
    }

    public void setResultZ(final String resultZ) {
        this.resultZ = resultZ;
    }

    public String getLog() {
        if (logger == null) { return ""; }
        StringBuilder result = new StringBuilder();
        for (String msg : logger.getLog()) {
            result.append(msg);
            result.append(System.lineSeparator());
        }
        return result.toString();
    }
}
