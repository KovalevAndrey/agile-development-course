package ru.unn.agile.calculating;

public class StringCalcViewModel {

    private final String logMessage = "\"Calculate\" button was pressed with params";
    public String status;
    public String expression;
    public String result;
    private ILogger log;

    public StringCalcViewModel(ILogger log) {
        status = "Type expression and click \"Calculate\"";
        expression = "";
        result = "";
        if (null == log) throw new RuntimeException("Log is null");
        this.log = log;
    }

    public ILogger getLog() {
        return log;
    }

    public void processCalculate() {
        try {
            log.putMessage("\"Calculate\" button was pressed with expr "
                    + expression);
            double res = StringCalculator.calculate(expression);
            result = String.valueOf(res);
            status = "Success";
        } catch (ArithmeticException e) {
            log.putMessage("Exception occurred: " + e.getMessage());
            status = e.getMessage();
            result = "";
        } catch (IllegalArgumentException e) {
            log.putMessage("Exception occurred: " + e.getMessage());
            status = e.getMessage();
            result = "";
        }
    }
}
