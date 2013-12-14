package ru.unn.agile.CreditCalculator.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

public class CreditCalculatorViewModelLoggerTest {
    protected CreditCalculatorViewModel creditCalculatorViewModel;

    @Before
    public void setUp() {
        FakeLogger testLogger = new FakeLogger();
        creditCalculatorViewModel = new CreditCalculatorViewModel(testLogger);
    }

    @After
    public void tearDown() {
        creditCalculatorViewModel = null;
    }

    @Test
    public void canCreateCreditCalculatorViewModelWithLogger() {
        FakeLogger logger = new FakeLogger();
        CreditCalculatorViewModel creditCalculatorViewModel = new CreditCalculatorViewModel(logger);

        assertNotNull(creditCalculatorViewModel);
    }

    @Test
    public void creditCalculatorViewModelConstructorThrowsExceptionWithNullLogger() {
        try
        {
            new CreditCalculatorViewModel(null);
            fail("Exception wasn't thrown");
        }
        catch(IllegalArgumentException ex)
        {
            assertEquals("Argument 'logger' must not be null", ex.getMessage());
        }
        catch(Exception ex)
        {
            fail("Invalid exception type");
        }
    }

    @Test
    public void setCreditAmountStringMethodWriteMessageToLogger() {
        String creditAmountString = "10000";
        creditCalculatorViewModel.setCreditAmountString(creditAmountString);
        String message = CreditCalculatorViewModel.LogMessages.INPUT_DATA_CREDIT_AMOUNT_HAS_CHANGED_TO + "'" + creditAmountString + "'";
        assertEquals(1, creditCalculatorViewModel.getLog().size());
        assertEquals(true, RegexHelper.IsMessageOfLogString(message, creditCalculatorViewModel.getLog().get(0)));
    }

    @Test
    public void multipleCallingOfSetCreditAmountStringMethodWithTheSameArgumentValuesWriteSingleMessageToLogger() {
        String creditAmountString = "10000";
        creditCalculatorViewModel.setCreditAmountString(creditAmountString);
        creditCalculatorViewModel.setCreditAmountString(creditAmountString);
        creditCalculatorViewModel.setCreditAmountString(creditAmountString);
        creditCalculatorViewModel.setCreditAmountString(creditAmountString);
        creditCalculatorViewModel.setCreditAmountString(creditAmountString);
        String message = CreditCalculatorViewModel.LogMessages.INPUT_DATA_CREDIT_AMOUNT_HAS_CHANGED_TO + "'" + creditAmountString + "'";
        assertEquals(1, creditCalculatorViewModel.getLog().size());
        assertEquals(true, RegexHelper.IsMessageOfLogString(message, creditCalculatorViewModel.getLog().get(0)));
    }

    @Test
    public void setMonthsCountStringMethodWriteMessageToLogger() {
        String monthsCountString = "24";
        creditCalculatorViewModel.setMonthsCountString(monthsCountString);
        String message = CreditCalculatorViewModel.LogMessages.INPUT_DATA_MONTHS_COUNT_HAS_CHANGED_TO + "'" + monthsCountString + "'";
        assertEquals(1, creditCalculatorViewModel.getLog().size());
        assertEquals(true, RegexHelper.IsMessageOfLogString(message, creditCalculatorViewModel.getLog().get(0)));
    }

    @Test
    public void multipleCallingOfSetMonthsCountStringMethodWithTheSameArgumentValuesWriteSingleMessageToLogger() {
        String monthsCountString = "24";
        creditCalculatorViewModel.setMonthsCountString(monthsCountString);
        creditCalculatorViewModel.setMonthsCountString(monthsCountString);
        creditCalculatorViewModel.setMonthsCountString(monthsCountString);
        creditCalculatorViewModel.setMonthsCountString(monthsCountString);
        creditCalculatorViewModel.setMonthsCountString(monthsCountString);
        String message = CreditCalculatorViewModel.LogMessages.INPUT_DATA_MONTHS_COUNT_HAS_CHANGED_TO + "'" + monthsCountString + "'";
        assertEquals(1, creditCalculatorViewModel.getLog().size());
        assertEquals(true, RegexHelper.IsMessageOfLogString(message, creditCalculatorViewModel.getLog().get(0)));
    }

    @Test
    public void setPercentStringMethodWriteMessageToLogger() {
        String percentString = "8";
        creditCalculatorViewModel.setPercentString(percentString);
        String message = CreditCalculatorViewModel.LogMessages.INPUT_DATA_PERCENTS_HAS_CHANGED_TO + "'" + percentString + "'";
        assertEquals(1, creditCalculatorViewModel.getLog().size());
        assertEquals(true, RegexHelper.IsMessageOfLogString(message, creditCalculatorViewModel.getLog().get(0)));
    }

    @Test
    public void multipleCallingOfSetPercentStringMethodWithTheSameArgumentValuesWriteSingleMessageToLogger() {
        String percentString = "8";
        creditCalculatorViewModel.setPercentString(percentString);
        creditCalculatorViewModel.setPercentString(percentString);
        creditCalculatorViewModel.setPercentString(percentString);
        creditCalculatorViewModel.setPercentString(percentString);
        creditCalculatorViewModel.setPercentString(percentString);
        String message = CreditCalculatorViewModel.LogMessages.INPUT_DATA_PERCENTS_HAS_CHANGED_TO + "'" + percentString + "'";
        assertEquals(1, creditCalculatorViewModel.getLog().size());
        assertEquals(true, RegexHelper.IsMessageOfLogString(message, creditCalculatorViewModel.getLog().get(0)));
    }

    @Test
    public void setPaymentTypeStringMethodWithDefaultAnnuityPaymentTypeArgumentValueDoNotWriteMessageToLogger() {
        CreditCalculatorViewModel.PaymentType paymentType = CreditCalculatorViewModel.PaymentType.ANNUITY;
        creditCalculatorViewModel.setPaymentType(paymentType);
        assertEquals(0, creditCalculatorViewModel.getLog().size());
    }

    @Test
    public void setPaymentTypeStringMethodWriteMessageToLogger() {
        CreditCalculatorViewModel.PaymentType paymentType = CreditCalculatorViewModel.PaymentType.DIFFERENTIATED;
        creditCalculatorViewModel.setPaymentType(paymentType);
        String message = CreditCalculatorViewModel.LogMessages.PAYMENT_TYPE_HAS_CHANGED_TO + "'" + paymentType.toString() + "'";
        assertEquals(1, creditCalculatorViewModel.getLog().size());
        assertEquals(true, RegexHelper.IsMessageOfLogString(message, creditCalculatorViewModel.getLog().get(0)));
    }

    @Test
    public void multipleCallingOfSetPaymentTypeStringMethodWithTheSameArgumentValuesWriteSingleMessageToLogger() {
        CreditCalculatorViewModel.PaymentType paymentType = CreditCalculatorViewModel.PaymentType.DIFFERENTIATED;
        creditCalculatorViewModel.setPaymentType(paymentType);
        creditCalculatorViewModel.setPaymentType(paymentType);
        creditCalculatorViewModel.setPaymentType(paymentType);
        creditCalculatorViewModel.setPaymentType(paymentType);
        creditCalculatorViewModel.setPaymentType(paymentType);
        String message = CreditCalculatorViewModel.LogMessages.PAYMENT_TYPE_HAS_CHANGED_TO + "'" + paymentType.toString() + "'";
        assertEquals(1, creditCalculatorViewModel.getLog().size());
        assertEquals(true, RegexHelper.IsMessageOfLogString(message, creditCalculatorViewModel.getLog().get(0)));
    }

    @Test
    public void calculateMethodWithDefaultCreditDataWriteMessageAboutFailedAttemptOfCalculationToLogger() {
        creditCalculatorViewModel.calculate();
        String message = CreditCalculatorViewModel.LogMessages.MONTHLY_PAYMENT_HAS_NOT_CALCULATED;
        assertEquals(1, creditCalculatorViewModel.getLog().size());
        assertEquals(true, RegexHelper.IsMessageOfLogString(message, creditCalculatorViewModel.getLog().get(0)));
    }

    @Test
    public void calculateMethodWithUncompletedCreditDataWriteMessageAboutFailedAttemptOfCalculationToLogger() {
        creditCalculatorViewModel.setPercentString("10");
        creditCalculatorViewModel.setMonthsCountString("12");
        creditCalculatorViewModel.calculate();
        String message = CreditCalculatorViewModel.LogMessages.MONTHLY_PAYMENT_HAS_NOT_CALCULATED;
        int logSize = creditCalculatorViewModel.getLog().size();
        assertEquals(true, RegexHelper.IsMessageOfLogString(message, creditCalculatorViewModel.getLog().get(logSize - 1)));
    }

    @Test
    public void calculateMethodWithWrongCreditDataWriteMessageAboutSuccessfulCalculationToLogger() {
        creditCalculatorViewModel.setPercentString("a");
        creditCalculatorViewModel.setMonthsCountString("12");
        creditCalculatorViewModel.setMonthsCountString("30000");
        creditCalculatorViewModel.calculate();
        String message = CreditCalculatorViewModel.LogMessages.MONTHLY_PAYMENT_HAS_NOT_CALCULATED;
        int logSize = creditCalculatorViewModel.getLog().size();
        assertEquals(true, RegexHelper.IsMessageOfLogString(message, creditCalculatorViewModel.getLog().get(logSize - 1)));
    }

    @Test
    public void calculateMethodWithCompletedCreditDataWriteMessageAboutSuccessfulCalculationToLogger() {
        creditCalculatorViewModel.setPercentString("10");
        creditCalculatorViewModel.setMonthsCountString("12");
        creditCalculatorViewModel.setCreditAmountString("30000");
        creditCalculatorViewModel.calculate();
        String message = CreditCalculatorViewModel.LogMessages.MONTHLY_PAYMENT_HAS_CALCULATED;
        int logSize = creditCalculatorViewModel.getLog().size();
        assertEquals(true, RegexHelper.IsMessageOfLogString(message, creditCalculatorViewModel.getLog().get(logSize - 1)));
    }
}
