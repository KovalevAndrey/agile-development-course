package ru.unn.agile.VolumeCalculator.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

public class VolumeCalculatorViewModelTest {
    public VolumeCalculatorViewModel viewModel;

    @Before
    public void beforeTest() {
        FakeLogger logger=new FakeLogger();
        viewModel = new VolumeCalculatorViewModel(logger);
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
    public void resultMustBeCorrectAfterCalculationVolumeCube(){
        String arg1="2";
        String arg2="";
        viewModel.typeFigure= VolumeCalculatorViewModel.TypeFigure.CUBE;
        viewModel.calculate(arg1,arg2);
        assertEquals("8.0", viewModel.result);
    }

    @Test
    public void resultMustBeCorrectAfterCalculationVolumeSphere(){
        String arg1="2";
        String arg2="";
        viewModel.typeFigure= VolumeCalculatorViewModel.TypeFigure.SPHERE;
        viewModel.calculate(arg1,arg2);
        assertEquals("33.510321638291124", viewModel.result);
    }

    @Test
    public void resultMustBeCorrectAfterCalculationVolumeCone(){
        String arg1="2";
        String arg2="3";
        viewModel.typeFigure= VolumeCalculatorViewModel.TypeFigure.CONE;
        viewModel.calculate(arg1,arg2);
        assertEquals("12.566370614359172", viewModel.result);
    }

    @Test
    public void resultMustBeCorrectAfterCalculationVolumeSquarePyramid(){
        String arg1="3";
        String arg2="2";
        viewModel.typeFigure= VolumeCalculatorViewModel.TypeFigure.SQUARE_PYRAMID;
        viewModel.calculate(arg1,arg2);
        assertEquals("9.0", viewModel.result);
    }

    @Test
    public void resultMustBeCorrectAfterCalculationVolumeCylinder(){
        String arg1="2";
        String arg2="1.5";
        viewModel.typeFigure= VolumeCalculatorViewModel.TypeFigure.CYLINDER;
        viewModel.calculate(arg1,arg2);
        assertEquals("18.84955592153876", viewModel.result);
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

    @Test (expected = IllegalArgumentException.class)
    public void nullLoggerIsNotAllowed() {
        viewModel = new VolumeCalculatorViewModel(null);
    }

    @Test
    public void canAcceptNotNullLogger() {
        assertNotNull(viewModel);
    }

    @Test
    public void canGetViewModelLog() {
        assertNotNull(viewModel.getLog());
    }

    @Test
    public void loggerIsEmptyAtStart() {
        assertEquals(0, viewModel.getLog().size());
    }

    @Test
    public void loggerMustBeEmptyAfterClear(){
        viewModel.setNameAndVisibleForArguments(VolumeCalculatorViewModel.TypeFigure.SQUARE_PYRAMID);
        viewModel.calculate("1","2");
        viewModel.clearLog();
        assertEquals(true,viewModel.getLog().isEmpty());
    }

    @Test
    public void afterCalculateLoggerMustHaveTwoRecord(){
        viewModel.calculate("1","2");
        assertEquals(2,viewModel.getLog().size());
    }

    @Test
    public void afterCorrectCalculateLoggerMustHaveInfoRecord(){
        viewModel.setNameAndVisibleForArguments(VolumeCalculatorViewModel.TypeFigure.CONE);
        viewModel.calculate("1","2");
        assertEquals(true, viewModel.getLog().get(2).indexOf("Info:")>=0);
    }

    @Test
    public void afterNonCorrectCalculateLoggerMustHaveErrorRecord(){
        viewModel.setNameAndVisibleForArguments(VolumeCalculatorViewModel.TypeFigure.CYLINDER);
        viewModel.calculate("1","a");
        assertEquals(true, viewModel.getLog().get(2).indexOf("Error:")>=0);
    }

    @Test
    public void afterSetNameAndVisibleForArgumentsLoggerMustHaveInfoRecord(){
        viewModel.setNameAndVisibleForArguments(VolumeCalculatorViewModel.TypeFigure.CUBE);
        assertEquals(true, viewModel.getLog().get(0).indexOf("Info:")>=0);
    }

    @Test
    public void afterCorrectCalculateGetLastMassageGiveCorrespondRecord(){
        viewModel.setNameAndVisibleForArguments(VolumeCalculatorViewModel.TypeFigure.CUBE);
        viewModel.calculate("1","");
        assertEquals(true, viewModel.getLastMessageFromLog().indexOf("Info:")>=0);
    }

    @Test
    public void afterNonCorrectCalculateGetLastMassageGiveCorrespondRecord(){
        viewModel.setNameAndVisibleForArguments(VolumeCalculatorViewModel.TypeFigure.SPHERE);
        viewModel.calculate("q","");
        assertEquals(true, viewModel.getLastMessageFromLog().indexOf("Error:")>=0);
    }

    @Test
    public void forEmptyLoggerGetLastMassageGiveEmptyString(){
        viewModel.clearLog();
        assertEquals("", viewModel.getLastMessageFromLog());
    }

    @Test
    public void logHaveCorrespondRecordAfterSelectCone(){
        viewModel.setNameAndVisibleForArguments(VolumeCalculatorViewModel.TypeFigure.CONE);
        assertEquals(true, viewModel.getLastMessageFromLog().indexOf(VolumeCalculatorViewModel.TypeFigure.CONE.toString())>=0);
    }

    @Test
    public void logHaveCorrespondRecordAfterSelectSphere(){
        viewModel.setNameAndVisibleForArguments(VolumeCalculatorViewModel.TypeFigure.SPHERE);
        assertEquals(true, viewModel.getLastMessageFromLog().indexOf(VolumeCalculatorViewModel.TypeFigure.SPHERE.toString())>=0);
    }

    @Test
    public void logHaveCorrespondRecordAfterSelectCube(){
        viewModel.setNameAndVisibleForArguments(VolumeCalculatorViewModel.TypeFigure.CUBE);
        assertEquals(true, viewModel.getLastMessageFromLog().indexOf(VolumeCalculatorViewModel.TypeFigure.CUBE.toString())>=0);
    }

    @Test
    public void logHaveCorrespondRecordAfterSelectCylinder(){
        viewModel.setNameAndVisibleForArguments(VolumeCalculatorViewModel.TypeFigure.CYLINDER);
        assertEquals(true, viewModel.getLastMessageFromLog().indexOf(VolumeCalculatorViewModel.TypeFigure.CYLINDER.toString())>=0);
    }

    @Test
    public void logHaveCorrespondRecordAfterSelectSquarePyramid(){
        viewModel.setNameAndVisibleForArguments(VolumeCalculatorViewModel.TypeFigure.SQUARE_PYRAMID);
        assertEquals(true, viewModel.getLastMessageFromLog().indexOf(VolumeCalculatorViewModel.TypeFigure.SQUARE_PYRAMID.toString())>=0);
    }
}
