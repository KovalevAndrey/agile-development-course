package ru.unn.agile.Re.viewmodel;

import ru.unn.agile.Re.model.Re;
import ru.unn.agile.Re.model.Regex;

import java.awt.Color;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegexViewModel
{
    public String pattern;
    public String text;
    public String status;

    private String lastPattern = Status.NOTHING_FOUND;
    private Regex regex;
    private ILogger log;
    private Map<String, String> inputText;

    public RegexViewModel(ILogger log)
    {
        pattern = "";
        text = "";
        status = "";
        inputText = new HashMap<String, String>();

        if(log == null)
        {
            throw new RuntimeException("Logger is null");
        }
        this.log = log;
    }

    public void search()
    {
        final String TAG = "search";
        try
        {
            status = getStatusMessage(findFirstOccurrence());
            log.i(TAG, status);
        }
        catch (Exception e)
        {
            status = e.getMessage();
            log.e(TAG, status);
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

    public void focusLost(String tag, String text)
    {
        if (text.equals(""))
        {
            log.e(tag, "empty field");
            return;
        }
        if (!text.equals(inputText.get(tag)))
        {
            inputText.put(tag, text);
            log.i(tag, text);
        }
        else
        {
            log.w(tag, "value isn't change: " + text);
        }
    }

    public List<String[]> getLog()
    {
        return log.getLog();
    }

    public Color getRowColor(String logType)
    {
        Color rowColor = Color.WHITE;
        if (ILogger.INFO.equals(logType))
        {
            rowColor = Color.LIGHT_GRAY;
        }
        else if (ILogger.WARN.equals(logType))
        {
            rowColor = Color.YELLOW;
        }
        else if (ILogger.ERROR.equals(logType))
        {
            rowColor = Color.MAGENTA;
        }
        return rowColor;
    }
}

abstract class Status
{
    public static final String NOTHING_FOUND = "Nothing found";
    public static final String FIRST_OCCURRENCE_IS = "First occurrence is: ";
}
