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
    public boolean inputWasChanged = false;
    public ILogger log;

    public ViewModel(ILogger log) {
        if(log == null)
            throw new IllegalArgumentException("Argument 'log' shouldn't be null");

        this.log = log;
    }

    public void processKeyInTextField(int keyCode) {
        if (keyCode == ENTER_CODE) {
            Search();
        } else {
            inputWasChanged = true;
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

    public void focusLost() {
        if (inputWasChanged) {
            LogChangedInput();
            inputWasChanged = false;
        }
    }

    public void LogChangedInput() {
        String newNote = Events.INPUT_WAS_CHANGED;

        newNote += "listOfElements = " + listOfElements;
        newNote += "; key = " + key;

        log.AddNote(newNote);
    }

    public void LogSearch() {
        String newNote = Events.SEARCH_WAS_PRESSED;

        newNote += "Input: ";
        newNote += "listOfElements = " + listOfElements;
        newNote += "; key = " + key;

        newNote += ". Output: ";
        newNote += "status = " + status;
        newNote += "; keyIndex = " + keyIndex;

        log.AddNote(newNote);

    }

    public void Search() {
        if (!parseInput()) {
            LogSearch();
            return;
        }

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
            keyIndex = Integer.toString(index + 1);
        }

        LogSearch();
    }

    public int[] ConvertStringToIntArray(String inputStr) {
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
        public static final String READY = "Press 'Search' or Enter";
        public static final String BAD_FORMAT = "Bad format";
        public static final String SUCCESS = "Success";
        public static final String NOT_FOUND = "The list of elements doesn't have this key";
        public static final String UNSORTED = "The list of elements should be sorted";
    }

    public class Events {
        public static final String INPUT_WAS_CHANGED = "Input was changed to: ";
        public static final String SEARCH_WAS_PRESSED = "Search. ";
    }
}
