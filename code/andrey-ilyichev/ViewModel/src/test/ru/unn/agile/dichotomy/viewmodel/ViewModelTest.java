package ru.unn.agile.dichotomy.viewmodel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ViewModelTest {
	private ViewModel viewModel;
	public ILogger logger;
	
	private final float delta = 0.01F;
	
	@Before
	public void setUp() {
		logger = new VirtualLogger();
	}
	
	@After
	public void setDown() {
		viewModel = null;
	}
	
	private void assertEqualsWithRegularExpr(String expression, String testString){
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(testString);
        assertEquals(true, matcher.matches());
    }
	
	@Test
	public void defaultValuesAreCorrect() {
		
		viewModel = new ViewModel.Builder().logger(logger).build();
		
		assertEquals("",viewModel.getA());
		assertEquals("",viewModel.getB());
		assertEquals("",viewModel.getSigma());
		assertEquals("",viewModel.getEps());
		assertEquals("",viewModel.getResultMessage());
		assertEquals(ViewModel.Function.FunctionLnOfXplusOne, viewModel.getFunction());
		
		assertEquals(0,viewModel.getLogSize());
	}
	
	@Test 
	public void correctMinimumOfFunctionLnOfXplusOneWithCorrectInputData() {
		viewModel = new ViewModel.Builder().a("0").b("1").sigma("0.1").eps("0.01").function(ViewModel.Function.FunctionLnOfXplusOne).logger(logger).build();
		
		viewModel.getResult();
		
		float expected = 0;
		float actual = Float.valueOf(viewModel.getResultMessage());
		assertEquals(expected, actual, delta);
	}
	
	@Test 
	public void correctMinimumOfFunctionSqrXminusOneWithCorrectInputData() {
		viewModel = new ViewModel.Builder().a("-1").b("2").sigma("0.4").eps("0.01").function(ViewModel.Function.FunctionSqrXminusOne).logger(logger).build();
		
		viewModel.getResult();
		
		float expected = -1;
		float actual = Float.valueOf(viewModel.getResultMessage());
		assertEquals(expected, actual, delta);
	}
	
	@Test 	
	public void resultIsBadFormatIfDataIsNotFull() {
		viewModel = new ViewModel.Builder().b("2").sigma("0.4").eps("0.01").function(ViewModel.Function.FunctionSqrXminusOne).logger(logger).build();
		
		viewModel.getResult();
		
		assertEquals("Bad Format", viewModel.getResultMessage());
	}

	@Test 	
	public void resultIsBadFormatIfDataIsNotCorrectForEntering() {
		viewModel = new ViewModel.Builder().a("a").b("2").sigma("0.4").eps("0.01").function(ViewModel.Function.FunctionSqrXminusOne).logger(logger).build();
		
		viewModel.getResult();
		
		assertEquals("Bad Format", viewModel.getResultMessage());
	}
	
	@Test 	
	public void resultIsDataIsInvalidIfDataIsNotCorrectForAlgorithm() {
		viewModel = new ViewModel.Builder().a("-1").b("2").sigma("10").eps("0.01").function(ViewModel.Function.FunctionSqrXminusOne).logger(logger).build();
		
		viewModel.getResult();
		
		assertEquals("Data is invalid for algorithm", viewModel.getResultMessage());
	}
	
	@Test
	public void correctCreationViewModelWithLogger(){
		VirtualLogger vLogger = new VirtualLogger();
		this.viewModel = new ViewModel.Builder().logger(vLogger).build();
		
		assertNotNull(this.viewModel);
	}
	
	@Test
	public void resultIsLoggerHasNullPointerIfConstructorViewModelGotNull(){

		this.viewModel = new ViewModel.Builder().build();
		
		viewModel.getResult();
		
		assertEquals("Logger is null", viewModel.getResultMessage());
	}

	@Test
	public void correctAddNewRecordToLogAfterCreation(){
		viewModel = new ViewModel.Builder().logger(logger).build();
		viewModel.getResult();
		
		ArrayList<String> logList = this.viewModel.getLog();
		String logRecord = logList.get(logList.size()-1);
		
		String stdRecord = ".*a = ; b = ; eps = ; sigma = ; function is log10\\(x\\+1\\); resultMessage = Bad Format";

		assertEquals(1, viewModel.getLogSize());
		assertEqualsWithRegularExpr( stdRecord, logRecord);
	}
	
	@Test
	public void correctAddNewRecordToLogAfterCalculationMinimimOfFunctionLnOfXplusOneWithCorrectInputData(){
		viewModel = new ViewModel.Builder().a("0").b("1").sigma("0.1").eps("0.01").function(ViewModel.Function.FunctionLnOfXplusOne).logger(logger).build();
		
		viewModel.getResult();
		
		ArrayList<String> logList = this.viewModel.getLog();
		String logRecord = logList.get(logList.size()-1);
	
		assertEqualsWithRegularExpr(".*a = 0; b = 1; eps = 0.01; sigma = 0.1; function is log10\\(x\\+1\\); resultMessage = 0.0036319997", logRecord);
	}
	
	@Test
	public void correctAddNewRecordToLogAfterCalculationMinimimOfFunctionSqrXminusOneWithCorrectInputData(){
		viewModel = new ViewModel.Builder().a("-1").b("2").sigma("0.4").eps("0.01").function(ViewModel.Function.FunctionSqrXminusOne).logger(logger).build();
		
		viewModel.getResult();
		
		ArrayList<String> logList = this.viewModel.getLog();
		String logRecord = logList.get(logList.size()-1);
		
		assertEqualsWithRegularExpr(".*a = -1; b = 2; eps = 0.01; sigma = 0.4; function is x\\^2-1; resultMessage = -0.999999", logRecord);
	}
	
	@Test
	public void correctAddNewRecordToLogAfterGettingBadFormatData(){
		viewModel = new ViewModel.Builder().a("a").b("2").sigma("0.4").eps("0.01").function(ViewModel.Function.FunctionSqrXminusOne).logger(logger).build();
		
		viewModel.getResult();
		
		ArrayList<String> logList = this.viewModel.getLog();
		String logRecord = logList.get(logList.size()-1);
		
		
		assertEqualsWithRegularExpr(".*a = a; b = 2; eps = 0.01; sigma = 0.4; function is x\\^2-1; resultMessage = Bad Format", logRecord);
	}
	
	@Test	
	public void correctAddNewRecordToLogAfterGettingInvalidDataForAlgorithm(){
		viewModel = new ViewModel.Builder().a("-1").b("2").sigma("10").eps("0.01").function(ViewModel.Function.FunctionSqrXminusOne).logger(logger).build();
		
		viewModel.getResult();
		
		ArrayList<String> logList = this.viewModel.getLog();
		String logRecord = logList.get(logList.size()-1);
		
		assertEqualsWithRegularExpr(".*a = -1; b = 2; eps = 0.01; sigma = 10; function is x\\^2-1; resultMessage = Data is invalid for algorithm", logRecord);
	}
}