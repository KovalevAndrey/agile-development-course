package ru.unn.agile.Converter;

import java.util.List;

public class ViewModel
{
    public static final int ENTER_CODE = 10;
    public String inputNumber;
    public String result;
    public NumeralSystems inputSys;
    public NumeralSystems outputSys;
    public String status;
    public boolean isCalculateButtonEnabled;
    private ILogger logger;
    private Converter converter;

    public ViewModel(ILogger logger)
    {
        if (logger == null) throw new IllegalArgumentException("Logger parameter can't be null");

        inputNumber = "";
        result = "";
        inputSys = NumeralSystems.Binary;
        outputSys = NumeralSystems.Binary;
        status = Status.WAITING;
        this.logger = logger;
        isCalculateButtonEnabled  = false;
        converter = new Converter();
    }

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
        try
        {
            if (!inputNumber.isEmpty()) converter.tryConvert(inputNumber, inputSys.sys);
        }
        catch (Exception e)
        {
            status = Status.BAD_FORMAT;
            logger.log(BadFormatMessage());
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
        logger.log(CalculateMessage());
        if (!parseInput())
        {
            logger.log(BadFormatMessage());
            return;
        }

        try
        {
            result = new String(converter.ConvertFromOneToOther(inputNumber, inputSys.sys, outputSys.sys));
            logger.log(SuccessMessage());
            status = Status.SUCCESS;
        }
        catch (Exception e)
        {
            e.getMessage();
        }
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

    public class LogMessages
    {
        public static final String CALC = "Converting: ";
        public static final String BAD_FORMAT = "Bad format: ";
        public static final String SUCCESS = "Converted, the result is ";
    }

    public List<String> getLog() {
        return logger.getLog();
    }

    private String CalculateMessage()
    {
        String message = LogMessages.CALC + "'" + inputNumber + "'" + " from " + inputSys + " system to " + outputSys + " system";
        return message;
    }

    private String BadFormatMessage()
    {
        String message = LogMessages.BAD_FORMAT + "'" + inputNumber + "'" + " is not belong to " + inputSys + " system";
        return message;
    }

    private String SuccessMessage()
    {
        String message = LogMessages.SUCCESS + "'" + result + "'" + " in " + outputSys + " system";
        return message;
    }
}
