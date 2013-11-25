package ru.unn.agile.CreditCalculator.viewmodel;

import java.util.List;

public interface ILogger
{
    void Log(String s);

    List<String> getLog();
}
