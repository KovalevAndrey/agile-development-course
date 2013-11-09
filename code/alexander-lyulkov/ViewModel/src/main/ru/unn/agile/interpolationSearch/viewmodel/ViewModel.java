package ru.unn.agile.interpolationSearch.viewmodel;

import ru.unn.agile.interpolationSearch.InterpolationSearch;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: sasha
 * Date: 11/9/13
 * Time: 6:03 PM
 * To change this template use File | Settings | File Templates.
 */
public class ViewModel {
    public static final int ENTER_CODE = 10;
    public String listOfElements = "";
    public String key = "";
    public String keyIndex = "";
    public boolean isSearchButtonEnabled = false;
    public String status = Status.WAITING;

    public void processKeyInTextField(int keyCode) {
        if (keyCode == ENTER_CODE) {
            calculate();
        } else {
            parseInput();
        }
    }

    private boolean parseInput() {
        int[] ParsedListOfElements;
        int[] ParsedKey;
        boolean goodInput = true;

        try {
            ParsedListOfElements = ConvertStringToIntArray(listOfElements);
            ParsedKey = ConvertStringToIntArray(key);
            if (ParsedKey.length != 1){
                goodInput = false;
            }
            if (ParsedListOfElements.length < 1) {
                goodInput = false;
            }
            for(int i = 0; i < ParsedListOfElements.length - 1; i++){
                if (ParsedListOfElements[i] > ParsedListOfElements[i+1]){
                    status = Status.UNSORTED;
                    isSearchButtonEnabled = false;
                    return false;
                }
            }
        } catch (Exception e) {
            status = Status.BAD_FORMAT;
            isSearchButtonEnabled = false;
            return false;
        }

        if (goodInput == false) {
            if ((key.length() !=0) && (listOfElements.length() != 0)) {
                status = Status.BAD_FORMAT;
                isSearchButtonEnabled = false;
                return false;
            }else {
                status = Status.WAITING;
                isSearchButtonEnabled = false;
                return false;
            }
        }

        isSearchButtonEnabled = true;
        status = Status.READY;

        return true;
    }

    public void calculate() {
        if (!parseInput()) return;

        int[] Elements;
        int Key;
        Elements = ConvertStringToIntArray(listOfElements);
        Key = ConvertStringToIntArray(key)[0];

        InterpolationSearch searcher = new InterpolationSearch();
        int index = searcher.Search(Elements, Key);

        if (index == InterpolationSearch.NOT_FOUND) {
            status = Status.NOT_FOUND;
            keyIndex = "";
        } else {
            status = Status.SUCCESS;
            keyIndex = Integer.toString(index);
        }

    }

    public int[] ConvertStringToIntArray(String inputStr) {
        //ArrayList<Integer> listOfNumbers = new ArrayList<Integer>();
        Pattern digitalsPattern = Pattern.compile("-?[0-9]+");
        int numberOfElements = 0;

        Matcher matcher = digitalsPattern.matcher(inputStr);
        while (matcher.find()) {
            Integer.parseInt(matcher.group());
            numberOfElements++;
        }

        int[] resultArray = new int[numberOfElements];
        matcher = digitalsPattern.matcher(inputStr);
        for(int i = 0; i < numberOfElements; i++) {
            matcher.find();
            resultArray[i] = Integer.parseInt(matcher.group());

        }

        return resultArray;
    }

    public class Status {
        public static final String WAITING = "Please provide input data";
        public static final String READY = "Press 'Calculate' or Enter";
        public static final String BAD_FORMAT = "Bad format";
        public static final String SUCCESS = "Success";
        public static final String NOT_FOUND = "The list of elements doesn't have this key";
        public static final String UNSORTED = "The list of elements should be sorted";
    }
}
