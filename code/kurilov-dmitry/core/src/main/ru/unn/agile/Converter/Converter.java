package ru.unn.agile.Converter;
import java.lang.Math;

public class Converter
{
	private static String dictionary = "0123456789abcdef";

    public  Converter()
    {

    }

    public String ConvertToDecimal(String input, int baseFrom)
    {
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

    public String ConvertFromDecimal(String input, int baseTo)
    {
        input = input.trim().toLowerCase();
        int inputValue = Integer.valueOf(input);
        String output_str = new String("");
        while (inputValue != 0)
        {
            int indexInDictionry = inputValue % baseTo;
            output_str = (dictionary.substring(indexInDictionry, indexInDictionry + 1)) + output_str;
            inputValue = inputValue / baseTo;
        }
        return output_str;
    }

    public  String ConvertFromOneToOther(String input, int baseFrom, int baseTo)
    {
        input = input.trim().toLowerCase();
        String subResult = ConvertToDecimal(input, baseFrom);
        String outStr = ConvertFromDecimal(subResult, baseTo);
        return outStr;
    }
    public boolean OpportunityOfConvert(String input, int baseFrom)
    {
        input = input.trim().toLowerCase();
        int inputLength = input.length();
        boolean trigger = true;
        for (int i = 0; i < inputLength; i++)
        {
            if ((dictionary.indexOf(input.charAt(i)) >= baseFrom) || (dictionary.indexOf(input.charAt(i)) > 15) || (dictionary.indexOf(input.charAt(i)) < 0))
            {
                trigger = false;
                break;
            }
        }
        return trigger;
    }
}
