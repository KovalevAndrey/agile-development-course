package ru.unn.agile.Re.model;

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
        for (String descriptor : descriptors)
        {
            if (pattern.length() == 1 && pattern.indexOf(descriptor) != Regex.NOT_FOUND_INDEX)
            {
                throw new RuntimeException(ReError.BAD_PATTERN);
            }
        }

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
