package ru.unn.agile.VolumeCalculator.viewmodel;

import ru.unn.agile.VolumeCalculator.model.VolumeCalculator;

import java.util.List;

public class VolumeCalculatorViewModel {
    public String arg1;
    public String arg2;
    public TypeFigure typeFigure;
    public String result;
    public String status;
    public String nameArg1;
    public String nameArg2;
    public boolean isVisibleTextFieldArg2;

    ILogger logger;

    public VolumeCalculatorViewModel(ILogger logger) {
        if (logger == null)
            throw new IllegalArgumentException("Logger can't be null");
        this.logger=logger;
        init();
    }

    public void setNameAndVisibleForArguments(VolumeCalculatorViewModel.TypeFigure typeFigure){
        this.typeFigure=typeFigure;
        switch (typeFigure){
            case CUBE:{
                nameArg1=ArgumentsName.SIDE;
                isVisibleTextFieldArg2 =false;
                nameArg2="";
                logger.logInfo("Select "+ TypeFigure.CUBE);
                break;
            }
            case SPHERE:{
                nameArg1=ArgumentsName.RADIUS;
                isVisibleTextFieldArg2 =false;
                nameArg2="";
                logger.logInfo("Select "+ TypeFigure.SPHERE);
                break;
            }
            case SQUARE_PYRAMID:{
                nameArg1=ArgumentsName.SIDE;
                nameArg2=ArgumentsName.HEIGHT;
                isVisibleTextFieldArg2 =true;
                logger.logInfo("Select "+ TypeFigure.SQUARE_PYRAMID);
                break;
            }
            case CONE:{
                nameArg1=ArgumentsName.RADIUS;
                nameArg2=ArgumentsName.HEIGHT;
                isVisibleTextFieldArg2 =true;
                logger.logInfo("Select "+ TypeFigure.CONE);
                break;
            }
            case CYLINDER:{
                nameArg1=ArgumentsName.RADIUS;
                nameArg2=ArgumentsName.HEIGHT;
                isVisibleTextFieldArg2 =true;
                logger.logInfo("Select "+ TypeFigure.CYLINDER);
                break;
            }
        }
        status=Status.WAITING;
        result="";
    }

    public void calculate(String arg_1, String arg_2) {
        try {
            logger.logInfo("Try calculate volume "+typeFigure+". Arg1="+arg_1+"; Agr2="+arg_2);
            double arg1= Double.valueOf(arg_1);
            double arg2;
            double resultCalculation=0;
            switch (typeFigure){
                case CUBE: {
                    resultCalculation=VolumeCalculator.calculateVolumeCube(arg1);
                    break;
                }
                case SPHERE:{
                    resultCalculation=VolumeCalculator.calculateVolumeSphere(arg1);
                    break;
                }
                case SQUARE_PYRAMID:{
                    arg2 = Double.valueOf(arg_2);
                    resultCalculation=VolumeCalculator.calculateVolumeSquarePyramid(arg1,arg2);
                    break;
                }
                case CONE:{
                    arg2 = Double.valueOf(arg_2);
                    resultCalculation=VolumeCalculator.calculateVolumeCone(arg1,arg2);
                    break;
                }
                case CYLINDER:{
                    arg2 = Double.valueOf(arg_2);
                    resultCalculation=VolumeCalculator.calculateVolumeCylinder(arg1,arg2);
                    break;
                }
            }
            status=Status.SUCCESS;
            logger.logInfo(Status.SUCCESS+" Result="+resultCalculation);
            result=String.valueOf(resultCalculation);
        }
        catch (IllegalArgumentException e){
            status=Status.ERROR_INPUT_ARGUMENT;
            logger.logError(Status.ERROR_INPUT_ARGUMENT);
            result="";
        }
    }

    public List<String> getLog() {
        return logger.getAllMessage();
    }

    public String getLastMessageFromLog() {
        return logger.getLastMessage();
    }

    public void clearLog() {
        logger.clearLog();
    }

    private void init(){
        arg1 = "";
        arg2 = "";
        typeFigure = TypeFigure.CUBE;
        result = "";
        status = Status.WAITING;
        nameArg1= ArgumentsName.SIDE;
        nameArg2= "";
        isVisibleTextFieldArg2 =false;
    }

    public enum TypeFigure {
        CUBE("Cube"),
        SPHERE("Sphere"),
        SQUARE_PYRAMID("SquarePyramid"),
        CONE("Cone"),
        CYLINDER("Cylinder");

        private final String name;

        private TypeFigure(String name) {
            this.name = name;
        }

        public String toString() {
            return name;
        }
    }

    public class Status {
        public static final String WAITING = "Please provide input data";
        public static final String ERROR_INPUT_ARGUMENT = "Error: Input arguments is not valid";
        public static final String SUCCESS = "Success";
    }

    public class ArgumentsName{
        public static final String SIDE= "Side";
        public static final String RADIUS = "Radius";
        public static final String HEIGHT= "Height";
    }

}
