package ru.unn.agile.Re.model;

class AtTheBeginningOfTheLineRegex extends Regex
{
    public static final String descriptor = "^";

    private boolean isPatternApplicable(String pattern)
    {
        return pattern.substring(0, 1).equals(descriptor);
    }

    AtTheBeginningOfTheLineRegex(String pattern)
    {
        if (!isPatternApplicable(pattern))
        {
            throw new RuntimeException(ReError.BAD_PATTERN);
        }

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
            if (tokens[i].indexOf(lexemes.get(0)) == 0)
            {
                return position;
            }
            position += tokens[i].length() + "\n".length();
        }
        return Regex.NOT_FOUND_INDEX;
    }
}
