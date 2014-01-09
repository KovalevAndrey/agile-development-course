package ru.unn.agile.CreditCalculator.viewmodel;

import ru.unn.agile.creditcalculator.CreditCalculator;

import java.util.List;

public class CreditCalculatorViewModel {
    private String creditAmountString = "";
    private String monthsCountString = "";
    private String percentString = "";
    private PaymentType paymentType = PaymentType.ANNUITY;
    private String result = "";
    private String status = Status.BAD_FORMAT;
    private boolean isCalculateButtonEnabled = false;
    private ILogger logger;

    public CreditCalculatorViewModel(ILogger logger)
    {
        if (logger == null)
            throw new IllegalArgumentException("Argument 'logger' must not be null");
        this.logger = logger;
    }

    public void processKeyInTextField(int keyCode) {
        if (keyCode == SpecialKeys.ENTER) {
            calculate();
        } else {
            parseInput();
        }
    }

    private boolean isInputNotEmpty() {
        return !creditAmountString.isEmpty() && !monthsCountString.isEmpty() && !percentString.isEmpty();
    }

    private boolean isCorrectData(double creditAmount, int monthCount, double percent) {
        return isInputNotEmpty() && creditAmount > 0 && monthCount > 0 && percent >= 0;
    }

    private boolean parseInput() {
        double creditAmount = 0.0;
        int monthCount = 0;
        double percent = 0.0;
        try {
            if (!creditAmountString.isEmpty())
                creditAmount = Double.parseDouble(creditAmountString);
            if (!monthsCountString.isEmpty())
                monthCount = Integer.parseInt(monthsCountString);
            if (!percentString.isEmpty())
                percent = Double.parseDouble(percentString);
        } catch (Exception e) {
            status = Status.BAD_FORMAT;
            isCalculateButtonEnabled = false;
            return false;
        }

        isCalculateButtonEnabled = isCorrectData(creditAmount, monthCount, percent);
        if (isCalculateButtonEnabled) {
            status = Status.READY;
        } else {
            status = Status.BAD_FORMAT;
        }

        return isCalculateButtonEnabled;
    }

    public void calculate() {
        if (!parseInput()){
            logger.Log(LogMessages.MONTHLY_PAYMENT_HAS_NOT_CALCULATED);
            return;
        }

        CreditCalculator creditCalculator = convertToCreditCalculator(creditAmountString, monthsCountString, percentString);

        switch (paymentType) {
            case ANNUITY:
                result = String.valueOf(creditCalculator.getAnnuityTotalPayment());
                break;
            case DIFFERENTIATED:
                result = String.valueOf(creditCalculator.getDifferentiatedTotalPayment());
                break;
        }

        status = Status.SUCCESS;
        logger.Log(LogMessages.MONTHLY_PAYMENT_HAS_CALCULATED);
    }

    public CreditCalculator convertToCreditCalculator(String creditAmountString, String monthsCountString, String percentString) {
        return new CreditCalculator.Builder()
                .monthsCount(Integer.parseInt(monthsCountString))
                .amount(Double.parseDouble(creditAmountString))
                .percent(Double.parseDouble(percentString))
                .build();
    }

    public String getCreditAmountString()
    {
        return creditAmountString;
    }

    public void setCreditAmountString(String creditAmountString)
    {
        if (this.creditAmountString.equals(creditAmountString))
            return;
        this.creditAmountString = creditAmountString;
        String message = LogMessages.INPUT_DATA_CREDIT_AMOUNT_HAS_CHANGED_TO + "'" + creditAmountString + "'";
        logger.Log(message);
    }

    public String getMonthsCountString()
    {
        return monthsCountString;
    }

    public void setMonthsCountString(String monthsCountString)
    {
        if (this.monthsCountString.equals(monthsCountString))
            return;
        this.monthsCountString = monthsCountString;
        String message = LogMessages.INPUT_DATA_MONTHS_COUNT_HAS_CHANGED_TO + "'" + monthsCountString + "'";
        logger.Log(message);
    }

    public String getPercentString()
    {
        return percentString;
    }

    public void setPercentString(String percentString)
    {
        if (this.percentString.equals(percentString))
            return;
        this.percentString = percentString;
        String message = LogMessages.INPUT_DATA_PERCENTS_HAS_CHANGED_TO + "'" + percentString + "'";
        logger.Log(message);
    }

    public PaymentType getPaymentType()
    {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType)
    {
        if (this.paymentType.equals(paymentType))
            return;
        this.paymentType = paymentType;
        String message = LogMessages.PAYMENT_TYPE_HAS_CHANGED_TO + "'" + paymentType.toString() + "'";
        logger.Log(message);
    }

    public String getResult()
    {
        return result;
    }

    public String getStatus()
    {
        return status;
    }

    public boolean getIsCalculateButtonEnabled()
    {
        return isCalculateButtonEnabled;
    }

    public List<String> getLog()
    {
        return logger.getLog();
    }

    public enum PaymentType {
        ANNUITY("Аннуитентный"),
        DIFFERENTIATED("Дифференцированный");
        private final String name;

        private PaymentType(String name) {
            this.name = name;
        }

        public String toString() {
            return name;
        }
    }

    public static class Status {
        public static final String READY = "Нажмите 'Рассчитать' или клавишу Enter";
        public static final String BAD_FORMAT = "Неверный формат данных";
        public static final String SUCCESS = "Рассчёт произведён";
    }

    public static class LogMessages {
        public static final String INPUT_DATA_CREDIT_AMOUNT_HAS_CHANGED_TO = "Input data 'credit amount' has changed to ";
        public static final String INPUT_DATA_MONTHS_COUNT_HAS_CHANGED_TO = "Input data 'months count' has changed to ";
        public static final String INPUT_DATA_PERCENTS_HAS_CHANGED_TO = "Input data 'percents' has changed to ";
        public static final String PAYMENT_TYPE_HAS_CHANGED_TO = "Payment type has changed to ";
        public static final String MONTHLY_PAYMENT_HAS_NOT_CALCULATED = "Monthly payment hasn't calculated. Invalid input data";
        public static final String MONTHLY_PAYMENT_HAS_CALCULATED = "Monthly payment has calculated. ";
    }
}
