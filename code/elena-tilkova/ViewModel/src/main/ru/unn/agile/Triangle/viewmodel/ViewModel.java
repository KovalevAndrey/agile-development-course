package ru.unn.agile.Triangle.viewmodel;

import ru.unn.agile.Triangle.Triangle;
import ru.unn.agile.Triangle.Point;

import java.util.List;

public class ViewModel
{
    public static final int ENTER_CODE = 10;
    public String pointA1 = "";
    public String pointA2 = "";
    public String pointB1 = "";
    public String pointB2 = "";
    public String pointC1 = "";
    public String pointC2 = "";
    public String status = "";
    public String result = "";
    public Operation operation = Operation.PERIMETR;
    public boolean isCalculateButtonEnabled = false;
    private ILogger logger;

    public ViewModel(ILogger logger)
    {
        if (logger != null)
            this.logger = logger;
        else
            throw new RuntimeException("null pointer");
    }

    public enum Operation
    {
        PERIMETR("Perimetr"),
        SQUARE("Square"),
        INRADIUS("InRadius"),
        CIRCUMRADIUS("Circumradius"),
        SIDELENGTHAB("sideLength AB"),
        SIDELENGTHBC("sideLength BC"),
        SIDELENGTHAC("sideLength AC"),
        ANGLEA("Angle A"),
        ANGLEB("Angle B"),
        ANGLEC("Angle C");

        private final String name;
        private Operation(String name)
        {
            this.name = name;
        }

        public String toString()
        {
            return name;
        }
    }

    public void enterKeyInTextField(int keyCode)
    {
        result = "";
        if (keyCode == ENTER_CODE)
        {
            calculate();
        }
        else
        {
            parseInput();
            logger.Log(logMessage(logEvent.UPDATE.toString()));
        }

    }

    private boolean isInputAvailable()
    {
        return !pointA1.isEmpty() && !pointA2.isEmpty() && !pointB1.isEmpty() && !pointB2.isEmpty() && !pointC1.isEmpty() && !pointC2.isEmpty();
    }

    private boolean parseInput()
    {
        try
        {
            if (!pointA1.isEmpty()) Double.parseDouble(pointA1);
            if (!pointA2.isEmpty()) Double.parseDouble(pointA2);
            if (!pointB1.isEmpty()) Double.parseDouble(pointB1);
            if (!pointB2.isEmpty()) Double.parseDouble(pointB2);
            if (!pointC1.isEmpty()) Double.parseDouble(pointC1);
            if (!pointC2.isEmpty()) Double.parseDouble(pointC2);
        }
        catch (Exception e)
        {
            status = "Bad format numbers";
            isCalculateButtonEnabled = false;
            return false;
        }
        isCalculateButtonEnabled = isInputAvailable();
        if (isCalculateButtonEnabled)
        {
            status = "Data is correct";
        } else
        {
            status = "";
        }
        return isCalculateButtonEnabled;
    }

    public void calculate()
    {
        if (parseInput())
        {
            Point A = new Point(Double.parseDouble(pointA1), Double.parseDouble(pointA2));
            Point B = new Point(Double.parseDouble(pointB1), Double.parseDouble(pointB2));
            Point C = new Point(Double.parseDouble(pointC1), Double.parseDouble(pointC2));

            double answer = 0;
            Triangle triangle;

            try
            {
                triangle = new Triangle(A, B, C);
            }
            catch (IllegalArgumentException e)
            {
                status = e.getMessage();
                logger.Log(logMessage(logEvent.CALCULATE.toString()));
                return;
            }

            switch (operation)
            {
                case PERIMETR:
                    answer = triangle.perimeter();
                    break;
                case SQUARE:
                    answer = triangle.square();
                    break;
                case INRADIUS:
                    answer = triangle.inRadius();
                    break;
                case CIRCUMRADIUS:
                    answer = triangle.circumradius();
                    break;
                case SIDELENGTHAB:
                    answer = triangle.sideLength("AB");
                    break;
                case SIDELENGTHBC:
                    answer = triangle.sideLength("BC");
                    break;
                case SIDELENGTHAC:
                    answer = triangle.sideLength("AC");
                    break;
                case ANGLEA:
                    answer = triangle.angle("A");
                    break;
                case ANGLEB:
                    answer = triangle.angle("B");
                    break;
                case ANGLEC:
                    answer = triangle.angle("C");
                    break;
            }
            result = Double.toString(answer);
            status = "Done";
        }
        logger.Log(logMessage(logEvent.CALCULATE.toString()));
    }

    public List<String> getLog()
    {
        List<String> log = logger.getLog();
        return log;
    }

    private String logMessage(String logStatus) {
        String message = logStatus +
                "PointA( " + pointA1 + "; " + pointA2 + "); " +
                "PointB( " + pointB1 + "; " + pointB2 + "); " +
                "PointC( " + pointC1 + "; " + pointC2 + "); " +
                "Operation: " + operation + "; " +
                "status: " + status;
        return message;
    }

    public int logDataSize()
    {
        return logger.dataSize();
    }

    private enum logEvent
    {
        UPDATE("update fields "),
        CALCULATE("calculate ");

        private final String name;
        private logEvent(String name)
        {
            this.name = name;
        }

        public String toString()
        {
            return name;
        }
    }
}
