package ru.unn.agile.CreditCalculator.infrastructure;

import ru.unn.agile.CreditCalculator.viewmodel.CreditCalculatorViewModel;
import ru.unn.agile.CreditCalculator.viewmodel.CreditCalculatorViewModelTest;

public class CreditCalculatorViewModelWithRealLoggerTest extends CreditCalculatorViewModelTest {
    @Override
    public void setUp() {
        RealLogger realLogger = new RealLogger("./CreditCalculatorViewModelWithRealLoggerTest.log");
        creditCalculatorViewModel = new CreditCalculatorViewModel(realLogger);
    }
}