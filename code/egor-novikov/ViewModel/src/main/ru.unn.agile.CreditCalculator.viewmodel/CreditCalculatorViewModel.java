package ru.unn.agile.CreditCalculator.viewmodel;

import ru.unn.agile.creditcalculator.CreditCalculator;

public class CreditCalculatorViewModel {
    public static final int ENTER_CODE = 10;
    public String creditAmountString = "";
    public String monthsCountString = "";
    public String percentString = "";
    public PaymentType paymentType = PaymentType.ANNUITY;
    public String result = "";
    public String status = Status.BAD_FORMAT;
    public boolean isCalculateButtonEnabled = false;

    public void processKeyInTextField(int keyCode) {
        if (keyCode == ENTER_CODE) {
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
        if (!parseInput()) return;

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
    }

    public CreditCalculator convertToCreditCalculator(String creditAmountString, String monthsCountString, String percentString) {
        return new CreditCalculator.Builder()
                .monthsCount(Integer.parseInt(monthsCountString))
                .amount(Double.parseDouble(creditAmountString))
                .percent(Double.parseDouble(percentString))
                .build();
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

    public class Status {
        public static final String READY = "Нажмите 'Рассчитать' или клавишу Enter";
        public static final String BAD_FORMAT = "Неверный формат данных";
        public static final String SUCCESS = "Рассчёт произведён";
    }
}
