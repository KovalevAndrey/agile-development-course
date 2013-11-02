package ru.unn.agile.ComplexNumber.viewmodel;

import ru.unn.agile.ComplexNumber.model.ComplexNumber;

//TODO: disable button if some fields are empty
//TODO: add some periodic notifications from Model
//TODO: We can populate the list manually

public class ViewModel
{
    public String re1;
    public String im1;
    public String re2;
    public String im2;
    public String result;
    public String status;

    public enum Operation { ADD, MULTIPLY }
    private Operation operation;

    public class Statuses {
        public static final String DEFAULT = "";
        public static final String BAD_FORMAT = "Bad format";
        public static final String SUCCESS = "Success";
    }

    public ViewModel() {
        re1 = ""; im1 = "";
        re2 = ""; im2 = "";
        operation = Operation.ADD;
        result = ""; status = Statuses.DEFAULT;
    }

    public void parseInputFields() {
        try {
            if (!re1.isEmpty()) Double.parseDouble(re1);
            if (!im1.isEmpty()) Double.parseDouble(im1);
            if (!re2.isEmpty()) Double.parseDouble(re2);
            if (!im2.isEmpty()) Double.parseDouble(im2);
        }
        catch (Exception e) {
            status = Statuses.BAD_FORMAT;
            return;
        }
        status = Statuses.DEFAULT;
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
            status = Statuses.BAD_FORMAT;
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
        status = Statuses.SUCCESS;
    }

    public void setOperation(String operation) {
        if (operation == "Add")
            this.operation = Operation.ADD;
        else if (operation == "Mul")
            this.operation = Operation.MULTIPLY;
        else
            throw new IllegalArgumentException();
    }

    public Operation getOperation() {
        return operation;
    }

    public ComplexNumber convertToComplexNumber(String re, String im) {
        return new ComplexNumber(Double.parseDouble(re), Double.parseDouble(im));
    }
}
