package ru.unn.agile.tree.viewmodel;

import ru.unn.agile.tree.model.Tree;

public class TreeViewModel {
    public String treeValues;
    public String findValue;
    public String result = "Result";

    public ClickHandler findActionHandler;

    public TreeViewModel() {
        findActionHandler = new ClickHandler() {
            @Override
            public void onClick() {
                TreeViewModel.this.bind();
                TreeViewModel.this.processFindAction();
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
}
