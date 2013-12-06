package ru.unn.agile.interpolationSearch.infrastructure;


import ru.unn.agile.interpolationSearch.viewmodel.ViewModel;
import ru.unn.agile.interpolationSearch.viewmodel.ViewModelTests;
/**
 * Created with IntelliJ IDEA.
 * User: sasha
 * Date: 12/5/13
 * Time: 11:38 PM
 * To change this template use File | Settings | File Templates.
 */
public class ViewModelWithTextLoggerTests extends ViewModelTests {
    @Override
    public void setUp() {
        TextLogger realLogger = new TextLogger("./ViewModelWithTxtLoggerTests.log");
        viewModel = new ViewModel(realLogger);
    }
}