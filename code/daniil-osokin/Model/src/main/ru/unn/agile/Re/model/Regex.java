package ru.unn.agile.Re.model;

import java.lang.Integer;
import java.lang.Override;
import java.lang.String;
import java.util.ArrayList;

public abstract class Regex
{
    public static final int NOT_FOUND_INDEX = -1;
    protected ArrayList<String> lexemes = new ArrayList<String>();

    public int search(String text)
    {
        final int size = lexemes.size();
        int[] positions = new int[size];
        for (int i = 0; i < size; i++)
        {
            positions[i] = text.indexOf(lexemes.get(i));
        }

        int minPosition = Integer.MAX_VALUE;
        for (int i = 0; i < size; i++)
        {
            if (isFoundEarlier(positions[i], minPosition))
            {
                minPosition = positions[i];
            }
        }

        minPosition = (minPosition != Integer.MAX_VALUE) ? minPosition : Regex.NOT_FOUND_INDEX;
        return minPosition;
    }

    private boolean isFoundEarlier(int currentPosition, int minPosition)
    {
        return currentPosition != Regex.NOT_FOUND_INDEX && currentPosition < minPosition;
    }
}

class CharacterRegex extends Regex
{
    CharacterRegex(String pattern)
    {
        lexemes.add(pattern);
    }
}

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

class AtTheBeginningOfTheLineRegex extends Regex
{
    public static final String descriptor = "^";

    AtTheBeginningOfTheLineRegex(String pattern)
    {
        String[] tokens = pattern.split("\\" + descriptor);
        lexemes.add(tokens[1]);
    }

    @Override
    public int search(String text)
    {
        String[] tokens = text.split("\n");
        int position = 0;
        for (int i = 0; i < tokens.length; i++)
        {
            if (tokens[i].indexOf(lexemes.get(0)) != Regex.NOT_FOUND_INDEX)
            {
                return position;
            }
            position += tokens[i].length() + "\n".length();
        }
        return Regex.NOT_FOUND_INDEX;
    }
}

class ExactNumberOfRepeatsRegex extends Regex
{
    public static final String descriptor = "{";

    ExactNumberOfRepeatsRegex(String pattern)
    {
        String singeExpression = pattern.substring(0, pattern.indexOf("{"));
        final int repeatNumber = Integer.parseInt(pattern.substring(pattern.indexOf("{") + 1, pattern.indexOf("}")));

        String repeatedExpression = "";
        for (int i = 0; i < repeatNumber; i++)
        {
            repeatedExpression += singeExpression;
        }
        repeatedExpression += pattern.substring(pattern.indexOf("}") + 1, pattern.length());
        lexemes.add(repeatedExpression);
    }
}

abstract class RegexFactory
{
    public static final String[] descriptors =
    {
        NullOrOneRepeatRegex.descriptor,
        ExactNumberOfRepeatsRegex.descriptor,
        AtTheBeginningOfTheLineRegex.descriptor
    };

    public static Regex create(String pattern)
    {
        if (pattern.indexOf(NullOrOneRepeatRegex.descriptor) != Regex.NOT_FOUND_INDEX)
        {
            return new NullOrOneRepeatRegex(pattern);
        }

        if (pattern.indexOf(ExactNumberOfRepeatsRegex.descriptor) != Regex.NOT_FOUND_INDEX)
        {
            return new ExactNumberOfRepeatsRegex(pattern);
        }

        if (pattern.indexOf(AtTheBeginningOfTheLineRegex.descriptor) != Regex.NOT_FOUND_INDEX)
        {
            return new AtTheBeginningOfTheLineRegex(pattern);
        }

        return new CharacterRegex(pattern);
    }
}
