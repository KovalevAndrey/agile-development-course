package ru.unn.agile.tree.viewmodel;

import ru.unn.agile.tree.model.Tree;

import java.util.List;

public class TreeViewModel {
    public String treeValues;
    public String findValue;
    public String result = "Result";
    private ILogger logger;

    public ClickHandler findActionHandler;

    public TreeViewModel(ILogger logger) {
        if(logger == null) {
            throw new IllegalArgumentException("Logger parameter cannot be null");
        }

        this.logger = logger;
        findActionHandler = new ClickHandler() {
            @Override
            public void onClick() {
                TreeViewModel.this.logger.log(LogConstants.FIND_BUTTON_PRESSED);

                TreeViewModel.this.bind();

                TreeViewModel.this.logger.log(LogConstants.INPUT_PARAMETERS);
                TreeViewModel.this.logger.log(LogConstants.TREE_VALUES + getLogTreeValues());
                TreeViewModel.this.logger.log(LogConstants.FIND_VALUE + getLogFindValue());
                TreeViewModel.this.logger.log(LogConstants.PROCESS_STARTED);

                TreeViewModel.this.processFindAction();

                TreeViewModel.this.logger.log(LogConstants.PROCESS_FINISHED);
                TreeViewModel.this.logger.log(LogConstants.RESULT + result);

                TreeViewModel.this.unbind();
            }
        };
    }

    public boolean isStringNumber(String string) {
        if (string == null || string.length() == 0) {
            return false;
        }

        try {
            int value = Integer.parseInt(string);
        }
        catch (NumberFormatException e) {
            return false;
        }

        return true;
    }
    
    public Tree parseStringToTree(String string) {
        if (string == null || string.length() == 0) {
            return null;
        }

        String[] numbers = string.split(",");
        Tree result = null;
        for (String number : numbers) {
            if (isStringNumber(number)) {
                if (result == null) {
                    result = new Tree(Integer.parseInt(number));
                }
                else {
                    result.insert(Integer.parseInt(number));
                }
            }
        }

        return result;
    }

    public void bind() {}
    public void unbind() {}

    public List<String> readLog()
    {
        return logger.readLog();
    }

    private void processFindAction() {
        Tree tree = parseStringToTree(treeValues);
        if (tree == null) {
            result = "Tree string has invalid format";
            return;
        }

        if (!isStringNumber(findValue)) {
            result = "Find value is invalid";
            return;
        }

        if (tree.find(Integer.parseInt(findValue)) == null) {
            result = "Value not found";
        }
        else {
            result = "Tree contains the value";
        }
    }

    private String getLogTreeValues() {
        if (treeValues != null && treeValues.length() > 0) {
            return treeValues;
        }
        else {
            return "<empty>";
        }
    }

    private String getLogFindValue() {
        if (findValue != null && findValue.length() > 0) {
            return findValue;
        }
        else {
            return "<empty>";
        }
    }

    public class LogConstants {
        public final static String FIND_BUTTON_PRESSED = "Find button was pressed";
        public final static String INPUT_PARAMETERS = "Input parameters:";
        public final static String TREE_VALUES = "Tree values are: ";
        public final static String FIND_VALUE = "Find value is: ";
        public final static String PROCESS_STARTED = "Process started";
        public final static String PROCESS_FINISHED = "Process finished";
        public final static String RESULT = "Result: ";
    }
}
