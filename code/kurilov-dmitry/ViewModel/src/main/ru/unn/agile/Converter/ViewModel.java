package ru.unn.agile.Converter;

public class ViewModel
{
    public static final int ENTER_CODE = 10;
    public String inputNumber = "";
    //public int inputBase = 10;
    //public int outputBase = 10;
    public String result = "";
    public Systems inputSys = Systems.Decimal;
    public Systems outputSys = Systems.Decimal;
    public String status = Status.WAITING;
    public boolean isCalculateButtonEnabled = false;

    public void processKeyInTextField(int keyCode) {
        if (keyCode == ENTER_CODE) {
            calculate();
        } else {
            parseInput();
        }
    }

    private boolean isInputAvailable() {
        return !inputNumber.isEmpty();
    }

    private boolean parseInput()
    {
        try
        {
            if (!inputNumber.isEmpty()) Integer.parseInt(inputNumber);
        }
        catch (Exception e)
        {
            status = Status.BAD_FORMAT;
            isCalculateButtonEnabled = false;
            return false;
        }

        isCalculateButtonEnabled = isInputAvailable();

        if (isCalculateButtonEnabled)
        {
            status = Status.READY;
        }
        else
        {
            status = Status.WAITING;
        }

        return isCalculateButtonEnabled;
    }

    public void calculate() {
        if (!parseInput()) return;

        Converter converter = new Converter();

        try
        {
            result = new String(converter.ConvertFromOneToOther(inputNumber, inputSys.sys, outputSys.sys));
            System.out.println(inputSys.sys);
            System.out.println(outputSys.sys);

        }
        catch (Exception e)
        {
            e.getMessage();
        }

        status = Status.SUCCESS;
    }

    public enum Systems
    {
        Binary(2),
        Octal(8),
        Decimal(10),
        Hexadecimal(16);
        private final int sys;

        private Systems(int syst)
        {
            this.sys = syst;
        }

        public int toInt()
        {
            return sys;
        }
    }

    public class Status
    {
        public static final String WAITING = "Please provide input data";
        public static final String READY = "Press 'Calculate' or Enter";
        public static final String BAD_FORMAT = "Bad format";
        public static final String SUCCESS = "Success";
    }
}
