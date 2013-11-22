package ru.unn.agile.stack;

import ru.unn.agile.stack.viewmodel.ViewModelStack;
import ru.unn.agile.stack.viewmodel.ViewModelStackTest;

public class ViewModelStackWithTxtLoggerTests extends ViewModelStackTest {
        @Override
        public void setUp() {
            TxtStackLogger realLogger = new TxtStackLogger("./ViewModelStackWithTxtLoggerTests.log");
            viewModel = new ViewModelStack(realLogger);
        }
}
