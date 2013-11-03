package ru.unn.agile.ComplexNumber.viewmodel;

import ru.unn.agile.ComplexNumber.model.ComplexNumber;

//TODO: add some periodic notifications from Model

public class ViewModel
{
    public String re1 = "";
    public String im1 = "";
    public String re2 = "";
    public String im2 = "";
    private Operation operation = Operation.ADD;
    public String result = "";
    public String status = Status.DEFAULT;
    public boolean isCalculateButtonEnabled = false;

    public enum Operation {
        ADD ("Add"),
        MULTIPLY ("Mul");

        private final String name;

        private Operation(String s) {
            name = s;
        }

        public String toString() {
            return name;
        }

        public boolean equalsName(String otherName) {
            return (otherName != null) && name.equals(otherName);
        }

        public static String[] getOperations() {
            int size = Operation.values().length;

            String[] operations = new String[size];

            for (int i = 0; i < size; i++) {
                operations[i] = Operation.values()[i].toString();
            }

            return operations;
        }
    }

    public class Status {
        public static final String DEFAULT = "";
        public static final String BAD_FORMAT = "Bad format";
        public static final String SUCCESS = "Success";
    }

    public void parseInput() {
        try {
            if (!re1.isEmpty()) Double.parseDouble(re1);
            if (!im1.isEmpty()) Double.parseDouble(im1);
            if (!re2.isEmpty()) Double.parseDouble(re2);
            if (!im2.isEmpty()) Double.parseDouble(im2);
        }
        catch (Exception e) {
            status = Status.BAD_FORMAT;
            isCalculateButtonEnabled = false;
            return;
        }

        status = Status.DEFAULT;
        isCalculateButtonEnabled = IsInputAvailable();
    }

    private boolean IsInputAvailable() {
        return !re1.isEmpty() && !im1.isEmpty() && !re2.isEmpty() && !im2.isEmpty();
    }

    public void calculate()
    {
        ComplexNumber z1, z2;
        try {
            z1 = convertToComplexNumber(re1, im1);
            z2 = convertToComplexNumber(re2, im2);
        }
        catch (Exception e) {
            result = "NA";
            status = Status.BAD_FORMAT;
            return;
        }

        ComplexNumber z3 = new ComplexNumber();
        switch (operation) {
            case ADD:
                z3 = z1.add(z2);
                break;
            case MULTIPLY:
                z3 = z1.multiply(z2);
                break;
        }

        result = z3.toString();
        status = Status.SUCCESS;
    }

    public void setOperation(String name) {
        if (Operation.ADD.equalsName(name)) {
            this.operation = Operation.ADD;
        }
        else if (Operation.MULTIPLY.equalsName(name)) {
            this.operation = Operation.MULTIPLY;
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    public Operation getOperation() {
        return operation;
    }

    public ComplexNumber convertToComplexNumber(String re, String im) {
        return new ComplexNumber(Double.parseDouble(re), Double.parseDouble(im));
    }
}
