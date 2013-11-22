package ru.unn.agile.Re.infrastructure;

import ru.unn.agile.Re.viewmodel.RegexViewModel;
import ru.unn.agile.Re.viewmodel.RegexViewModelLoggerTests;

public class ViewModelWithTxtLoggerTests extends RegexViewModelLoggerTests
{
    @Override
    public void setUp()
    {
        viewModel = new RegexViewModel(new TxtLogger("./ViewModelWithTxtLoggerTests.log"));
        pattern = "{2}a";
        viewModel.pattern = pattern;
        text = "simple text";
        viewModel.text = text;

        try
        {
            log = getLogger("log");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        TAG = getClass().getName();
    }
}
