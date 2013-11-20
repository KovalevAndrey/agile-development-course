package ru.unn.agile.Re.viewmodel;

import ru.unn.agile.Re.model.Re;
import ru.unn.agile.Re.model.Regex;

public class RegexViewModel
{
    public String pattern;
    public String text;
    public String status;

    private String lastPattern = Status.NOTHING_FOUND;
    private Regex regex;
    private ILogger logger;

    public RegexViewModel(ILogger logger)
    {
        pattern = "";
        text = "";
        status = "";

        if(logger == null)
        {
            throw new RuntimeException("Logger is null");
        }
        this.logger = logger;
    }

    public void search()
    {
        try
        {
            status = getStatusMessage(findFirstOccurrence());
        }
        catch (Exception e)
        {
            status = e.getMessage();
        }
    }

    protected int findFirstOccurrence()
    {
        if (!pattern.equals(lastPattern))
        {
            regex = Re.compile(pattern);
            lastPattern = pattern;
        }
        return regex.search(text);
    }

    private String getStatusMessage(int firstOccurrence)
    {
        String statusMessage = Status.NOTHING_FOUND;
        if (firstOccurrence != Regex.NOT_FOUND_INDEX)
        {
            statusMessage = Status.FIRST_OCCURRENCE_IS + firstOccurrence;
        }
        return statusMessage;
    }
}

abstract class Status
{
    public static final String NOTHING_FOUND = "Nothing found";
    public static final String FIRST_OCCURRENCE_IS = "First occurrence is: ";
}
