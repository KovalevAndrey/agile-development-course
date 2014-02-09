package ru.unn.agile.VolumeCalculator.model;

public class VolumeCalculator {

    public  static double calculateVolumeCube(double side){
        if (side < 0) throw new IllegalArgumentException("Side of cube must be >= 0!");
        return Math.pow(side,3);
    }

    public  static double calculateVolumeSphere(double radius){
        if (radius < 0) throw new IllegalArgumentException("Radius of sphere must be >= 0!");
        return 4*Math.PI*Math.pow(radius,3)/3;
    }

    public  static double calculateVolumeSquarePyramid(double side,double height){
        if (side < 0 || height < 0) throw new IllegalArgumentException("Side length of base and height must be >= 0!");
        return Math.pow(side,2)*height/2;
    }

    public  static double calculateVolumeCone(double radius, double height){
        if (radius < 0 || height<0) throw new IllegalArgumentException("radius of circle at base and height must be >= 0!");
        return Math.PI* Math.pow(radius,2)*height/3;
    }

    public  static double calculateVolumeCylinder(double radius, double height){
        if (radius < 0 || height<0) throw new IllegalArgumentException("radius of circle face and height must be >= 0!");
        return Math.PI*Math.pow(radius,2)*height;
    }

}
