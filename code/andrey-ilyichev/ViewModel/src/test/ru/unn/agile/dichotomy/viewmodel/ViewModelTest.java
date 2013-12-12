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
		viewModel = new ViewModel(logger);
	};
	
	@After
	public void setDown() {
		viewModel = null;
	};
	
	private void assertEqualsWithRegularExpr(String expression, String testString){
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(testString);
        assertEquals(true, matcher.matches());
    };
	
	@Test
	public void defaultValuesAreCorrect() {
		
		viewModel = new ViewModel(logger);
		
		assertEquals("",viewModel.getA());
		assertEquals("",viewModel.getB());
		assertEquals("",viewModel.getSigma());
		assertEquals("",viewModel.getEps());
		assertEquals("",viewModel.getResultMessage());
		assertEquals(ViewModel.Function.FunctionLnOfXplusOne, viewModel.getFunction());
		
		assertEquals(0,viewModel.getLogSize());
	};
	
	@Test 
	public void correctMinimumOfFunctionLnOfXplusOneWithCorrectInputData() {
		
		viewModel.setA("0");
		viewModel.setB("1");
		viewModel.setSigma ("0.1");
		viewModel.setEps("0.01");
		viewModel.setFunction(ViewModel.Function.FunctionLnOfXplusOne);
		
		viewModel.getResult();
		
		float expected = 0;
		float actual = Float.valueOf(viewModel.getResultMessage());
		assertEquals(expected, actual, delta);
	};
	
	@Test 
	public void correctMinimumOfFunctionSqrXminusOneWithCorrectInputData() {

		viewModel.setA("-1");
		viewModel.setB("2");
		viewModel.setSigma ("0.4");
		viewModel.setEps("0.01");
		viewModel.setFunction(ViewModel.Function.FunctionSqrXminusOne);
		
		viewModel.getResult();
		
		float expected = -1;
		float actual = Float.valueOf(viewModel.getResultMessage());
		assertEquals(expected, actual, delta);
	};
	
	@Test 	
	public void resultIsBadFormatIfDataIsNotFull() {

		viewModel.setB("2");
		viewModel.setSigma ("0.4");
		viewModel.setEps("0.01");
		viewModel.setFunction(ViewModel.Function.FunctionSqrXminusOne);
		viewModel.getResult();
		
		assertEquals("Bad Format", viewModel.getResultMessage());
	};

	@Test 	
	public void resultIsBadFormatIfDataIsNotCorrectForEntering() {

		viewModel.setA("a");
		viewModel.setB("2");
		viewModel.setSigma ("0.4");
		viewModel.setEps("0.01");
		viewModel.setFunction(ViewModel.Function.FunctionSqrXminusOne);
		
		viewModel.getResult();
		
		assertEquals("Bad Format", viewModel.getResultMessage());
	};
	
	@Test 	
	public void resultIsDataIsInvalidIfDataIsNotCorrectForAlgorithm() {
		
		viewModel.setA("-1");
		viewModel.setB("2");
		viewModel.setSigma ("10");
		viewModel.setEps("0.01");
		viewModel.setFunction(ViewModel.Function.FunctionSqrXminusOne);
		viewModel.getResult();
		
		assertEquals("Data is invalid for algorithm", viewModel.getResultMessage());
	};
	
	@Test
	public void correctCreationViewModelWithLogger(){
		VirtualLogger vLogger = new VirtualLogger();
		this.viewModel = new ViewModel(vLogger);
		
		assertNotNull(this.viewModel);
	};
	
	@Test
	public void resultIsLoggerHasNullPointerIfConstructorViewModelGotNull(){

		this.viewModel = new ViewModel(null);
		
		viewModel.getResult();
		
		assertEquals("Logger is null", viewModel.getResultMessage());
	};

	@Test
	public void correctAddNewRecordToLogAfterCreation(){
		viewModel = new ViewModel(logger);
		viewModel.getResult();
		
		ArrayList<String> logList = this.viewModel.getLog();
		String logRecord = logList.get(logList.size()-1);
		
		String stdRecord = ".*a = ; b = ; eps = ; sigma = ; function is log10\\(x\\+1\\); resultMessage = Bad Format";

		assertEquals(1, viewModel.getLogSize());
		assertEqualsWithRegularExpr( stdRecord, logRecord);
	};
	
	@Test
	public void can_Log_Calculation_Of_LnOfXplusOne_Minimum(){

		viewModel.setA("0");
		viewModel.setB("1");
		viewModel.setSigma ("0.1");
		viewModel.setEps("0.01");
		viewModel.setFunction(ViewModel.Function.FunctionLnOfXplusOne);
		
		viewModel.getResult();
		
		ArrayList<String> logList = this.viewModel.getLog();
		String logRecord = logList.get(logList.size()-1);
	
		assertEqualsWithRegularExpr(".*a = 0; b = 1; eps = 0.01; sigma = 0.1; function is log10\\(x\\+1\\); resultMessage = 0.0036319997", logRecord);
	};
	
	@Test
	public void can_Log_Calculation_Of_SqrXminusOne_Minimum(){
		
		viewModel.setA("-1");
		viewModel.setB("2");
		viewModel.setSigma ("0.4");
		viewModel.setEps("0.01");
		viewModel.setFunction(ViewModel.Function.FunctionSqrXminusOne);
		
		viewModel.getResult();
		
		ArrayList<String> logList = this.viewModel.getLog();
		String logRecord = logList.get(logList.size()-1);
		
		assertEqualsWithRegularExpr(".*a = -1; b = 2; eps = 0.01; sigma = 0.4; function is x\\^2-1; resultMessage = -0.999999", logRecord);
	};
	
	@Test
	public void can_Log_Bad_Format_Input(){
		
		viewModel.setA("a");
		viewModel.setB("2");
		viewModel.setSigma ("0.4");
		viewModel.setEps("0.01");
		viewModel.setFunction(ViewModel.Function.FunctionSqrXminusOne);
		
		viewModel.getResult();
		
		ArrayList<String> logList = this.viewModel.getLog();
		String logRecord = logList.get(logList.size()-1);
		
		
		assertEqualsWithRegularExpr(".*a = a; b = 2; eps = 0.01; sigma = 0.4; function is x\\^2-1; resultMessage = Bad Format", logRecord);
	};
	
	@Test	
	public void can_Log_Invalid_Input(){
		
		viewModel.setA("-1");
		viewModel.setB("2");
		viewModel.setSigma ("10");
		viewModel.setEps("0.01");
		viewModel.setFunction(ViewModel.Function.FunctionSqrXminusOne);
		viewModel.getResult();
		
		ArrayList<String> logList = this.viewModel.getLog();
		String logRecord = logList.get(logList.size()-1);
		
		assertEqualsWithRegularExpr(".*a = -1; b = 2; eps = 0.01; sigma = 10; function is x\\^2-1; resultMessage = Data is invalid for algorithm", logRecord);
	};
}