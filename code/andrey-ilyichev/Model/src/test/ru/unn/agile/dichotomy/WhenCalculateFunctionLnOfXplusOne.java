package ru.unn.agile.dichotomy;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class WhenCalculateFunctionLnOfXplusOne {
	private FunctionLnOfXplusOne function;
	private final float delta = 0.00001F;
	
	@Before
	public void setUp() {
		function = new FunctionLnOfXplusOne();
	}
	
	@Test
	public void zeroGivesZero() {
		float expected = 0;
		float actual = function.getValue(0);
		assertEquals(expected, actual, delta);
	}
}
