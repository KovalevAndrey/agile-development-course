package ru.unn.agile.VolumeCalculator.model;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class VolumeCalculatorTest {
    final double epsilon = 1e-12;

    @Test(expected = IllegalArgumentException.class)
    public void sideForCalculateVolumeCubeMustBeNotNegative(){
        VolumeCalculator.calculateVolumeCube(-1);
    }

    @Test
    public void calculateVolumeCubeWithZeroSideGiveCorrectResult(){
        assertEquals(0, VolumeCalculator.calculateVolumeCube(0),epsilon);
    }

    @Test
    public void calculateVolumeCubeGiveCorrectResult(){
        assertEquals(27.0, VolumeCalculator.calculateVolumeCube(3),epsilon);
    }

    @Test(expected = IllegalArgumentException.class)
    public void radiusForCalculateSphereVolumeMustBeNotNegative(){
        VolumeCalculator.calculateVolumeSphere(-1);
    }

    @Test
    public void calculateVolumeSphereWithZeroRadiusGiveCorrectResult(){
        assertEquals(0, VolumeCalculator.calculateVolumeSphere(0),epsilon);
    }

    @Test
    public void calculateVolumeSphereGiveCorrectResult(){
        assertEquals((4/3.)*Math.PI*Math.pow(4,3), VolumeCalculator.calculateVolumeSphere(4),epsilon);
    }

    @Test(expected = IllegalArgumentException.class)
    public void sideForCalculateSquarePyramidVolumeMustBeNotNegative(){
        VolumeCalculator.calculateVolumeSquarePyramid(-1,1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void heightForCalculateSquarePyramidVolumeMustBeNotNegative(){
        VolumeCalculator.calculateVolumeSquarePyramid(1,-1);
    }

    @Test
    public void calculateVolumeSquarePyramidWithZeroArgumentsGiveCorrectResult(){
        assertEquals(0, VolumeCalculator.calculateVolumeSquarePyramid(0,0),epsilon);
    }

    @Test
    public void calculateVolumeSquarePyramidGiveCorrectResult(){
        assertEquals(Math.pow(3.5,2)*4.2/2, VolumeCalculator.calculateVolumeSquarePyramid(3.5,4.2),epsilon);
    }

    @Test(expected = IllegalArgumentException.class)
    public void radiusForCalculateConeVolumeMustBeNotNegative(){
        VolumeCalculator.calculateVolumeCone(-1,1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void heightForCalculateConeVolumeMustBeNotNegative(){
        VolumeCalculator.calculateVolumeCone(1,-1);
    }

    @Test
    public void calculateVolumeConeWithZeroArgumentsGiveCorrectResult(){
        assertEquals(0, VolumeCalculator.calculateVolumeCone(0,0),epsilon);
    }

    @Test
    public void calculateVolumeConeGiveCorrectResult(){
        assertEquals(Math.PI* Math.pow(5.5,2)*2.5/3, VolumeCalculator.calculateVolumeCone(5.5,2.5),epsilon);
    }

    @Test(expected = IllegalArgumentException.class)
    public void radiusForCalculateCylinderVolumeMustBeNotNegative(){
        VolumeCalculator.calculateVolumeCylinder(-1,1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void heightForCalculateCylinderVolumeMustBeNotNegative(){
        VolumeCalculator.calculateVolumeCylinder(1,-1);
    }

    @Test
    public void calculateVolumeCylinderWithZeroArgumentsGiveCorrectResult(){
        assertEquals(0, VolumeCalculator.calculateVolumeCylinder(0,0),epsilon);
    }

    @Test
    public void calculateVolumeCylinderGiveCorrectResult(){
        assertEquals(Math.PI*Math.pow(5.7,2)*3.5, VolumeCalculator.calculateVolumeCylinder(5.7,3.5),epsilon);
    }
}
