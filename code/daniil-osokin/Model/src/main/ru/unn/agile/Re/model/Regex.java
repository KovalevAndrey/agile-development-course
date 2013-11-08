package ru.unn.agile.Re.model;

import java.util.ArrayList;

public abstract class Regex
{
    public static final int NOT_FOUND_INDEX = -1;
    protected ArrayList<String> lexemes = new ArrayList<String>();

    public int search(String text)
    {
        text = text.split("\n")[0];
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
