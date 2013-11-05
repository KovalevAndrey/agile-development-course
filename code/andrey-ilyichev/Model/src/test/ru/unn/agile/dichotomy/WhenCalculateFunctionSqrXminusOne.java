package ru.unn.agile.dichotomy;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class WhenCalculateFunctionSqrXminusOne {	
	private FunctionSqrXminusOne function;
	private final float delta = 0.00001F;
	
	@Before
	public void setUp() {
		function = new FunctionSqrXminusOne();
	}
	
	@Test
	public void zeroGivesMinusOne() {
		float expected = -1;
		float result = function.getValue(0);
		assertEquals(expected, result, delta);
	}
	
	@Test
	public void oneGivesZero() {
		float expected = 0;
		float result = function.getValue(1);
		assertEquals(expected, result, delta);
	}
}
