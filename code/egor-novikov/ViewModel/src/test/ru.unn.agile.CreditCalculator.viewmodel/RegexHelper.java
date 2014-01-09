package ru.unn.agile.CreditCalculator.viewmodel;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexHelper {
    private static final String DATE_PATTERN = "^\\(\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}\\): ";

    public static boolean IsMessageOfLogString(String message, String logString) {
        Pattern pattern = Pattern.compile(DATE_PATTERN + message);
        Matcher matcher = pattern.matcher(logString);
        return matcher.matches();
    }
}
