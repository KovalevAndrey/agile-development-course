package ru.unn.agile.Converter;
import java.lang.Math;

public class Converter
{
    private static String dictionary = "0123456789abcdef";
    private static int decimalBase = 10;

    public  Converter()
    {

    }

    public String ConvertToDecimal(String input, int baseFrom) throws Exception
    {
        tryConvert(input, baseFrom);
        input = input.trim().toLowerCase();
        int lengthOfInputString = input.length()-1;
        int outValue = 0;
        String outStr = new String("");
        for (int i = 0; i <= lengthOfInputString; i++)
        {
            outValue += (dictionary.indexOf(input.charAt(i))) * (Math.pow(baseFrom, (lengthOfInputString - i)));
        }
        return outStr = Integer.toString(outValue);
    }

    public String ConvertFromDecimal(String input, int baseTo) throws Exception
    {
        tryConvert(input, decimalBase);
        input = input.trim().toLowerCase();
        int inputValue = Integer.valueOf(input);
        String output_str = new String("");
        while (inputValue != 0)
        {
            int indexInDictionary = inputValue % baseTo;
            output_str = (dictionary.substring(indexInDictionary, indexInDictionary + 1)) + output_str;
            inputValue = inputValue / baseTo;
        }
        return output_str;
    }

    public  String ConvertFromOneToOther(String input, int baseFrom, int baseTo) throws Exception
    {
        input = input.trim().toLowerCase();
        String subResult = ConvertToDecimal(input, baseFrom);
        String outStr = ConvertFromDecimal(subResult, baseTo);
        return outStr;
    }
    public void tryConvert(String input, int baseFrom) throws Exception
    {
        input = input.trim().toLowerCase();
        int inputLength = input.length();

        if ((baseFrom < 2) || (baseFrom > 16))
        {
            throw new Exception("Основание системы не входит в обробатываемый диапазон.");
        }
        for (int i = 0; i < inputLength; i++)
        {
            if ((dictionary.indexOf(input.charAt(i)) >= baseFrom) || (dictionary.indexOf(input.charAt(i)) > 15) || (dictionary.indexOf(input.charAt(i)) < 0))
            {
                throw new Exception("Введённое число не принадлежит системе.");
            }
        }
    }
}
