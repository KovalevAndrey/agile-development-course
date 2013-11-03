package ru.unn.agile.Re.model;

class ExactNumberOfRepeatsRegex extends Regex
{
    public static final String descriptor = "{";
    private int repeatNumber;
    private int leftBraceIndex;
    private int rightBraceIndex;

    ExactNumberOfRepeatsRegex(String pattern)
    {
        if (!isPatternApplicable(pattern))
        {
            throw new RuntimeException(ReError.BAD_PATTERN);
        }

        String singeExpression = pattern.substring(0, leftBraceIndex);

        String repeatedExpression = "";
        for (int i = 0; i < repeatNumber; i++)
        {
            repeatedExpression += singeExpression;
        }
        repeatedExpression += pattern.substring(rightBraceIndex + 1, pattern.length());
        lexemes.add(repeatedExpression);
    }

    private boolean isPatternApplicable(String pattern)
    {
        leftBraceIndex = pattern.indexOf("{");
        rightBraceIndex = pattern.indexOf("}");
        if (leftBraceIndex + 1 >= rightBraceIndex)
        {
            return false;
        }
        if (rightBraceIndex - leftBraceIndex == pattern.length() - 1)
        {
            return false;
        }
        try
        {
            repeatNumber = Integer.parseInt(pattern.substring(leftBraceIndex + 1, rightBraceIndex));
        }
        catch (Exception e)
        {
            throw new RuntimeException(ReError.BAD_PATTERN);
        }
        return true;
    }
}
