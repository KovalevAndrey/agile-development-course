package ru.unn.agile.creditcalculator;

public class CreditCalculator
{
    private final int MONTH_PER_YEAR = 12;

    private final int HUNDRED = 100;

    private double amount;

    private int monthsCount;

    private double percent;

    public double getAmount()
    {
        return amount;
    }

    public void setAmount(double value)
    {
        amount = value;
    }

    public int getMonthsCount()
    {
        return monthsCount;
    }

    public void setMonthsCount(int value)
    {
        monthsCount = value;
    }

    public double getPercent()
    {
        return percent;
    }

    public void setPercent(double value)
    {
        percent = value;
    }

    public static class Builder
    {
        private double amount;

        private int monthsCount;

        private double percent;

        private boolean isAmountSetted;

        private boolean isMonthsCountSetted;

        private boolean isPercentSetted;

        public Builder() {}

        public Builder amount(double value)
        {
            checkAmount(value);
            amount = value;
            isAmountSetted = true;
            return this;
        }

        public Builder monthsCount(int value)
        {
            checkMonthsCount(value);
            monthsCount = value;
            isMonthsCountSetted = true;
            return this;
        }

        public Builder percent(double value)
        {
            checkPercent(value);
            percent = value;
            isPercentSetted = true;
            return this;
        }

        private void checkAmount(double amount)
        {
            if (amount <= 0.0)
                throw new IllegalArgumentException("amount argument value exception");
        }

        private void checkMonthsCount(double monthsCount)
        {
            if (monthsCount <= 0)
                throw new IllegalArgumentException("monthsCount argument value exception");
        }

        private void checkPercent(double percent)
        {
            if (percent < 0.0)
                throw new IllegalArgumentException("percent argument value exception");
        }

        private boolean isAllDataSetted()
        {
            return isAmountSetted && isMonthsCountSetted && isPercentSetted;
        }

        public CreditCalculator build()
        {
            if (!isAllDataSetted())
                throw new IllegalStateException("Not all required fields are filled");
            return new CreditCalculator(this);
        }
    }

    public CreditCalculator(Builder builder)
    {
        this.amount = builder.amount;
        this.monthsCount = builder.monthsCount;
        this.percent = builder.percent;
    }

    private void checkMonthNumber(int monthNumber)
    {
        if (monthNumber <= 0 || monthNumber > getMonthsCount())
            throw new IllegalArgumentException("monthNumber argument value exception");
    }

    public double getAnnuityMonthPayment()
    {
        double interestRateCoefficient = getPercent() / (MONTH_PER_YEAR * HUNDRED);
        double annuityCoefficient = interestRateCoefficient*Math.pow(1 + interestRateCoefficient, getMonthsCount()) / (Math.pow(1 + interestRateCoefficient, getMonthsCount()) - 1);
        return annuityCoefficient * getAmount();
    }

    public double getDifferentiatedMonthPayment(int monthNumber)
    {
        checkMonthNumber(monthNumber);
        double monthlyPrincipalPayment = getAmount() / getMonthsCount();
        return monthlyPrincipalPayment + (getAmount() - monthlyPrincipalPayment*(monthNumber-1)) * getPercent() / (MONTH_PER_YEAR * HUNDRED);
    }

    public double getAnnuityTotalPayment()
    {
        return getMonthsCount() * getAnnuityMonthPayment();
    }

    public double getDifferentiatedTotalPayment()
    {
        double result = 0.0;
        for (int i=1; i<getMonthsCount()+1; i++)
            result += getDifferentiatedMonthPayment(i);
        return result;
    }
}
