package ru.unn.agile.VolumeCalculator.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class VolumeCalculatorViewModelTest {
    public VolumeCalculatorViewModel viewModel;

    @Before
    public void beforeTest() {
        viewModel = new VolumeCalculatorViewModel();
    }

    @After
    public void afterTest() {
        viewModel = null;
    }

    @Test
    public void allFieldsHaveDefaultValuesAfterCreateForm(){
        assertEquals("", viewModel.arg1);
        assertEquals("", viewModel.arg2);
        assertEquals("", viewModel.result);
        assertEquals("", viewModel.nameArg2);
        assertEquals(false,viewModel.isVisibleTextFieldArg2);
        assertEquals(VolumeCalculatorViewModel.ArgumentsName.SIDE,viewModel.nameArg1);
        assertEquals(VolumeCalculatorViewModel.TypeFigure.CUBE, viewModel.typeFigure);
        assertEquals(VolumeCalculatorViewModel.Status.WAITING, viewModel.status);
    }

    @Test
    public void nameArgumentsHaveValidStateForCube(){
        viewModel.setNameAndVisibleForArguments(VolumeCalculatorViewModel.TypeFigure.CUBE);
        assertEquals(VolumeCalculatorViewModel.ArgumentsName.SIDE,viewModel.nameArg1);
        assertEquals("", viewModel.nameArg2);
    }

    @Test
    public void visibleArgumentsHaveValidStateForCube(){
        viewModel.setNameAndVisibleForArguments(VolumeCalculatorViewModel.TypeFigure.CUBE);
        assertEquals(false,viewModel.isVisibleTextFieldArg2);
    }

    @Test
    public void nameArgumentsHaveValidStateForSphere(){
        viewModel.setNameAndVisibleForArguments(VolumeCalculatorViewModel.TypeFigure.SPHERE);
        assertEquals("", viewModel.nameArg2);
        assertEquals(VolumeCalculatorViewModel.ArgumentsName.RADIUS,viewModel.nameArg1);
    }

    @Test
    public void visibleArgumentsHaveValidStateForSphere(){
        viewModel.setNameAndVisibleForArguments(VolumeCalculatorViewModel.TypeFigure.SPHERE);
        assertEquals(false,viewModel.isVisibleTextFieldArg2);
    }

    @Test
    public void nameArgumentsHaveValidStateForSquarePyramid(){
        viewModel.setNameAndVisibleForArguments(VolumeCalculatorViewModel.TypeFigure.SQUARE_PYRAMID);
        assertEquals(VolumeCalculatorViewModel.ArgumentsName.SIDE,viewModel.nameArg1);
        assertEquals(VolumeCalculatorViewModel.ArgumentsName.HEIGHT,viewModel.nameArg2);
    }

    @Test
    public void visibleArgumentsHaveValidStateForSquarePyramid(){
        viewModel.setNameAndVisibleForArguments(VolumeCalculatorViewModel.TypeFigure.SQUARE_PYRAMID);
        assertEquals(true,viewModel.isVisibleTextFieldArg2);
    }

    @Test
    public void nameArgumentsHaveValidStateForCone(){
        viewModel.setNameAndVisibleForArguments(VolumeCalculatorViewModel.TypeFigure.CONE);
        assertEquals(VolumeCalculatorViewModel.ArgumentsName.RADIUS,viewModel.nameArg1);
        assertEquals(VolumeCalculatorViewModel.ArgumentsName.HEIGHT,viewModel.nameArg2);
    }

    @Test
    public void visibleArgumentsHaveValidStateForCone(){
        viewModel.setNameAndVisibleForArguments(VolumeCalculatorViewModel.TypeFigure.CONE);
        assertEquals(true,viewModel.isVisibleTextFieldArg2);
    }

    @Test
    public void nameArgumentsHaveValidStateForCylinder(){
        viewModel.setNameAndVisibleForArguments(VolumeCalculatorViewModel.TypeFigure.CYLINDER);
        assertEquals(VolumeCalculatorViewModel.ArgumentsName.RADIUS,viewModel.nameArg1);
        assertEquals(VolumeCalculatorViewModel.ArgumentsName.HEIGHT,viewModel.nameArg2);
    }

    @Test
    public void visibleArgumentsHaveValidStateForCylinder(){
        viewModel.setNameAndVisibleForArguments(VolumeCalculatorViewModel.TypeFigure.CYLINDER);
        assertEquals(true,viewModel.isVisibleTextFieldArg2);
    }

    @Test
    public void inputArgumentsMustNotBeNegativeNumber(){
        String arg1="-1";
        String arg2="0";
        viewModel.typeFigure= VolumeCalculatorViewModel.TypeFigure.CONE;
        viewModel.calculate(arg1,arg2);
        assertEquals(VolumeCalculatorViewModel.Status.ERROR_INPUT_ARGUMENT, viewModel.status);
    }

    @Test
    public void inputArgumentsMustBeNumber(){
        String arg1="r";
        String arg2="0";
        viewModel.typeFigure= VolumeCalculatorViewModel.TypeFigure.SPHERE;
        viewModel.calculate(arg1,arg2);
        assertEquals(VolumeCalculatorViewModel.Status.ERROR_INPUT_ARGUMENT, viewModel.status);
    }

    @Test
    public void resultMustBeEmptyAfterNonCorrectCalculation(){
        String arg1="r";
        String arg2="0";
        viewModel.typeFigure= VolumeCalculatorViewModel.TypeFigure.SPHERE;
        viewModel.calculate(arg1,arg2);
        assertEquals("", viewModel.result);
    }

    @Test
    public void resultMustBeEmptyAfterChangeFigure(){
        viewModel.setNameAndVisibleForArguments(VolumeCalculatorViewModel.TypeFigure.SPHERE);
        assertEquals("", viewModel.result);
    }

    @Test
    public void resultMustBeNotEmptyAfterCorrectCalculation(){
        String arg1="2.2";
        String arg2="4.3";
        viewModel.typeFigure= VolumeCalculatorViewModel.TypeFigure.CONE;
        viewModel.calculate(arg1,arg2);
        assertNotEquals("", viewModel.result);
    }

    @Test
         public void statusAfterCorrectCalculationMustBeCorrespond(){
        String arg1="2.2";
        String arg2="4.3";
        viewModel.typeFigure= VolumeCalculatorViewModel.TypeFigure.SQUARE_PYRAMID;
        viewModel.calculate(arg1,arg2);
        assertEquals(VolumeCalculatorViewModel.Status.SUCCESS, viewModel.status);
    }

    @Test
    public void statusAfterChangeFigureMustBeWaiting(){
        viewModel.setNameAndVisibleForArguments(VolumeCalculatorViewModel.TypeFigure.SQUARE_PYRAMID);
        assertEquals(VolumeCalculatorViewModel.Status.WAITING, viewModel.status);
    }
}