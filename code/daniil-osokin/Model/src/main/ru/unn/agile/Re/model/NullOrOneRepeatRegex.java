package ru.unn.agile.Re.model;

class NullOrOneRepeatRegex extends Regex
{
    public static final String descriptor = "?";

    NullOrOneRepeatRegex(String pattern)
    {
        String[] tokens = pattern.split("\\" + descriptor);
        if (tokens.length == 1)
        {
            lexemes.add("");
            lexemes.add(tokens[0]);
        }
        else
        {
            lexemes.add(tokens[1]);
            lexemes.add(tokens[0] + tokens[1]);
        }
    }
}
