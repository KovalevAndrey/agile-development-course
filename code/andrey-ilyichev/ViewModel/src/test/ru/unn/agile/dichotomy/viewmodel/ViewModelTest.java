package ru.unn.agile.dichotomy.viewmodel;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ViewModelTest {
	private ViewModel viewModel;
	private final float delta = 0.01F;
	
	@Before
	public void setUp() {
		viewModel = new ViewModel();
	}
	
	@After
	public void setDown() {
		viewModel = null;
	}
	
	@Test
	public void defaultValuesAreCorrect() {
		assertEquals("",viewModel.a);
		assertEquals("",viewModel.b);
		assertEquals("",viewModel.sigma);
		assertEquals("",viewModel.eps);
		assertEquals("",viewModel.result);
		assertEquals(ViewModel.Function.FunctionLnOfXplusOne, viewModel.function);
	}
	
	@Test 
	public void correctMinimumOfFunctionLnOfXplusOneWithCorrectInputData() {
		viewModel.a = "0";
		viewModel.b = "1";
		viewModel.sigma = "0.1";
		viewModel.eps = "0.01";
		viewModel.function = ViewModel.Function.FunctionLnOfXplusOne;
		
		viewModel.getResult();
		
		float expected = 0;
		float actual = Float.valueOf(viewModel.result);
		assertEquals(expected, actual, delta);
	}

	@Test 
	public void correctMinOfFunctionSqrXminusOneWithCorrectInputData() {
		viewModel.a = "-1";
		viewModel.b = "2";
		viewModel.sigma = "0.4";
		viewModel.eps = "0.01";
		viewModel.function = ViewModel.Function.FunctionSqrXminusOne;
		
		viewModel.getResult();
		
		float expected = -1;
		float actual = Float.valueOf(viewModel.result);
		assertEquals(expected, actual, delta);
	}
	
	@Test 	
	public void resultIsBadFormatIfDataIsNotFull() {
		viewModel.b = "2";
		viewModel.sigma = "0.4";
		viewModel.eps = "0.01";
		viewModel.function = ViewModel.Function.FunctionSqrXminusOne;
		
		viewModel.getResult();
		
		assertEquals("Bad Format", viewModel.result);
	}
	
	@Test 	
	public void resultIsBadFormatIfDataIsNotCorrectForEntering() {
		viewModel.a = "a";
		viewModel.b = "2";
		viewModel.sigma = "0.4";
		viewModel.eps = "0.01";
		viewModel.function = ViewModel.Function.FunctionSqrXminusOne;
		
		viewModel.getResult();
		
		assertEquals("Bad Format", viewModel.result);
	}
	
	@Test 	
	public void resultIsDataIsInvalidIfDataIsNotCorrectForAlgorithm() {
		viewModel.a = "-1";
		viewModel.b = "2";
		viewModel.sigma = "10";
		viewModel.eps = "0.01";
		viewModel.function = ViewModel.Function.FunctionSqrXminusOne;
		
		viewModel.getResult();
		
		assertEquals("Data is invalid for algorithm", viewModel.result);
	}
}
