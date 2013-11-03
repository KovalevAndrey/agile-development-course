package ru.unn.agile.Re.model;

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
