package ru.unn.agile.calculating;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class StringCalcViewModelTest {

	private StringCalcViewModel viewModel;

	@Before
	public void beforeTest() {
		viewModel = new StringCalcViewModel();
	}

	@After
	public void afterTest() {
		viewModel = null;
	}

	@Test
	public void fieldsHaveDefaultValuesAfterCreating() {
		assertEquals("Type expression and click \"Calculate\"",
				viewModel.status);
		assertEquals("", viewModel.expression);
		assertEquals("", viewModel.result);
	}

	@Test
	public void canCalculateExpr() {
		viewModel.expression = "1 + 2 + 4";
		viewModel.processCalculate();
		assertEquals("7.0", viewModel.result);
	}
	
	@Test
	public void canCalculateExprWithParens() {
		viewModel.expression = "1 - (2 + 4)/2";
		viewModel.processCalculate();
		assertEquals("-2.0", viewModel.result);
	}

	@Test
	public void lettersInExprGivesNonSuccessStatusAndEmpryResult() {
		viewModel.expression = "1 + Af 2 + 4";
		assertExprCalculatingGivesError();
	}
	
	@Test
	public void wrongParensInExprGivesNonSuccessStatusAndEmpryResult() {
		viewModel.expression = "1 + ( 2 + 4";
		assertExprCalculatingGivesError();
	}
	
	@Test
	public void wrongOpsInExprGivesNonSuccessStatusAndEmpryResult() {
		viewModel.expression = "1 + + 2 + 4";
		assertExprCalculatingGivesError();	
	}
	
	private void assertExprCalculatingGivesError()
	{
		viewModel.processCalculate();
		assertEquals("", viewModel.result);
		assertNotEquals("Success", viewModel.status);
	}
}
