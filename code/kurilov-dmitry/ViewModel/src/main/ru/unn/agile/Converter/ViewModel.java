package ru.unn.agile.Converter;

public class ViewModel
{
    public static final int ENTER_CODE = 10;
    public String inputNumber = "";
    public String result = "";
    public NumeralSystems inputSys = NumeralSystems.Binary;
    public NumeralSystems outputSys = NumeralSystems.Binary;
    public String status = Status.WAITING;
    public boolean isCalculateButtonEnabled = false;

    public void processKeyInTextField(int keyCode)
    {
        if (keyCode == ENTER_CODE)
        {
            calculate();
        }
        else
        {
            parseInput();
        }
    }

    private boolean isInputAvailable()
    {
        return !inputNumber.isEmpty();
    }

    private boolean parseInput()
    {
        Converter converter = new Converter();
        try
        {
            if (!inputNumber.isEmpty()) converter.tryConvert(inputNumber, inputSys.sys);
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

    public void calculate()
    {
        if (!parseInput()) return;

        Converter converter = new Converter();

        try
        {
            result = new String(converter.ConvertFromOneToOther(inputNumber, inputSys.sys, outputSys.sys));
        }
        catch (Exception e)
        {
            e.getMessage();
        }

        status = Status.SUCCESS;
    }

    public enum NumeralSystems
    {
        Binary(2),
        Octal(8),
        Decimal(10),
        Hexadecimal(16);
        private final int sys;

        private NumeralSystems(int system)
        {
            this.sys = system;
        }

        public int toInt()
        {
            return sys;
        }
    }

    public class Status
    {
        public static final String WAITING = "Please provide input data";
        public static final String READY = "Press 'calculate' or Enter";
        public static final String BAD_FORMAT = "Bad format";
        public static final String SUCCESS = "Success";
    }
}
