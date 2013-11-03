package ru.unn.agile.Re.model;

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
