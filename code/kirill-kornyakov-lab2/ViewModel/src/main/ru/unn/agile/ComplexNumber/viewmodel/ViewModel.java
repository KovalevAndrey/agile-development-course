package ru.unn.agile.ComplexNumber.viewmodel;

import ru.unn.agile.ComplexNumber.model.ComplexNumber;

//TODO: remove item selection logic from View
//TODO: disable button if some fields are empty
//TODO: add some periodic notifications from Model

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

    public ViewModel() {
        re1 = "";
        im1 = "";
        re2 = "";
        im2 = "";
        operation = Operation.ADD;
        result = "";
        status = "";
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
            status = "Bad Format";
            return;
        }

        ComplexNumber resultZ = new ComplexNumber();
        switch (operation) {
            case ADD:
                resultZ = z1.add(z2);
                break;
            case MULTIPLY:
                resultZ = z1.multiply(z2);
                break;
        }

        result = resultZ.toString();
        status = "Success";
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
