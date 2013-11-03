package ru.unn.agile.dichotomy.viewmodel;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class ViewModelTest {
	private ViewModel viewModel;

	
	@Test
	public void defaultValuesAreCorrect(){
		viewModel = new ViewModel();
		assertEquals("",viewModel.a);
		assertEquals("",viewModel.b);
		assertEquals("",viewModel.sigma);
		assertEquals("",viewModel.eps);
		assertEquals("",viewModel.result);
		assertEquals(ViewModel.Function.FunctionLnOfXplusOne, viewModel.function);
	}
	
	@Test 
	public void correctMinFromFunctionLnOfXplusOneWithCorrectInputData(){
		viewModel = new ViewModel.Builder().a("0").b("1").sigma("0.1").eps("0.01").function(ViewModel.Function.FunctionLnOfXplusOne).build();
		
		viewModel.getResult();
		
		float expected = 0;
		float actual = Float.valueOf(viewModel.result);
		float delta = 0.01F;
		assertEquals(expected,actual,delta);
	}

	@Test 
	public void correctMinFromFunctionSqrXminusOneWithCorrectInputData(){
		viewModel = new ViewModel.Builder().a("-1").b("2").sigma("0.4").eps("0.01").function(ViewModel.Function.FunctionSqrXminusOne).build();
		
		viewModel.getResult();
		
		float expected = -1;
		float actual = Float.valueOf(viewModel.result);
		float delta = 0.01F;
		
		assertEquals(expected,actual,delta);
	}
	
	@Test 	
	public void resultIsBadFormatIfDataIsNotFull(){
		viewModel = new ViewModel.Builder().b("2").sigma("0.4").eps("0.01").function(ViewModel.Function.FunctionSqrXminusOne).build();
		
		viewModel.getResult();
		
		assertEquals("Bad Format", viewModel.result);
	}
	
	@Test 	
	public void resultIsBadFormatIfDataIsNotCorrectForEntering(){
		viewModel = new ViewModel.Builder().a("a").b("2").sigma("0.4").eps("0.01").function(ViewModel.Function.FunctionSqrXminusOne).build();
		
		viewModel.getResult();
		
		assertEquals("Bad Format", viewModel.result);
	}
	
	@Test 	
	public void resultIsDataIsInvalidIfDataIsNotCorrectForAlghorithm(){
		viewModel = new ViewModel.Builder().a("-1").b("2").sigma("10").eps("0.01").function(ViewModel.Function.FunctionSqrXminusOne).build();
		
		viewModel.getResult();
		
		assertEquals("Data is invalid for alghorithm", viewModel.result);
	}
}
