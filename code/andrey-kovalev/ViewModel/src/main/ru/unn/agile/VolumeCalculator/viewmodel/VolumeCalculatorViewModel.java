package ru.unn.agile.VolumeCalculator.viewmodel;

import ru.unn.agile.VolumeCalculator.model.VolumeCalculator;

public class VolumeCalculatorViewModel {
    public String arg1 = "";
    public String arg2 = "";
    public TypeFigure typeFigure = TypeFigure.CUBE;
    public String result = "";
    public String status = Status.WAITING;
    public String nameArg1= ArgumentsName.SIDE;
    public String nameArg2= "";
    public boolean isVisibleTextFieldArg2 =false;

    public void setNameAndVisibleForArguments(VolumeCalculatorViewModel.TypeFigure typeFigure){
        this.typeFigure=typeFigure;
        switch (typeFigure){
            case CUBE:{
                nameArg1=ArgumentsName.SIDE;
                isVisibleTextFieldArg2 =false;
                nameArg2="";
                break;
            }
            case SPHERE:{
                nameArg1=ArgumentsName.RADIUS;
                isVisibleTextFieldArg2 =false;
                nameArg2="";
                break;
            }
            case SQUARE_PYRAMID:{
                nameArg1=ArgumentsName.SIDE;
                nameArg2=ArgumentsName.HEIGHT;
                isVisibleTextFieldArg2 =true;
                break;
            }
            case CONE:{
                nameArg1=ArgumentsName.RADIUS;
                nameArg2=ArgumentsName.HEIGHT;
                isVisibleTextFieldArg2 =true;
                break;
            }
            case CYLINDER:{
                nameArg1=ArgumentsName.RADIUS;
                nameArg2=ArgumentsName.HEIGHT;
                isVisibleTextFieldArg2 =true;
                break;
            }
        }
        status=Status.WAITING;
        result="";
    }

    public void calculate(String agr_1, String agr_2) {
        try {
            double arg1= Double.valueOf(agr_1);
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
                    arg2 = Double.valueOf(agr_2);
                    resultCalculation=VolumeCalculator.calculateVolumeSquarePyramid(arg1,arg2);
                    break;
                }
                case CONE:{
                    arg2 = Double.valueOf(agr_2);
                    resultCalculation=VolumeCalculator.calculateVolumeCone(arg1,arg2);
                    break;
                }
                case CYLINDER:{
                    arg2 = Double.valueOf(agr_2);
                    resultCalculation=VolumeCalculator.calculateVolumeCylinder(arg1,arg2);
                    break;
                }
            }
            status=Status.SUCCESS;
            result=String.valueOf(resultCalculation);
        }
        catch (IllegalArgumentException e){
            status=Status.ERROR_INPUT_ARGUMENT;
            result="";
        }
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
