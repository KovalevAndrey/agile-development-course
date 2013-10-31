package ru.unn.agile.dichotomy;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class WhenCalculateDichotomy{

	private Dichotomy solver;

	@Test
	public void throwExceptionIfAMoreB(){
		try{
			solver = new Dichotomy.Builder().a(3).build();
			fail("expected illegalArgumentExeption");
		}catch(Exception ex){
			assertEquals("A more than B", ex.getMessage());
		}
	}
	
	@Test
	public void throwExceptionIfSigmaIsNegative(){
		try{
			solver = new Dichotomy.Builder().a(0).b(10).sigma(-0.4F).build();
			fail("expected illegalArgumentExeption");
		}catch(Exception ex){
			assertEquals("Sigma is incorrect", ex.getMessage());
		}
	}
	
	@Test
	public void throwExceptionIfSigmaIsZero(){
		try{
			solver = new Dichotomy.Builder().a(0).b(10).sigma(0).build();
			fail("expected illegalArgumentExeption");
		}catch(Exception ex){
			assertEquals("Sigma is incorrect", ex.getMessage());
		}
	}
	
	@Test
	public void throwExceptionIfSigmaIsBig(){
		try{
			solver = new Dichotomy.Builder().a(0).b(10).eps(0.1F).sigma(10).build();
			fail("expected illegalArgumentExeption");
		}catch(Exception ex){
			assertEquals("Sigma is incorrect", ex.getMessage());
		}
	}	
	
	@Test
	public void throwExceptionIfEpsIsNegative(){
		try{
			solver = new Dichotomy.Builder().a(0).b(10).sigma(1.F).eps(-1).build();
			fail("expected illegalArgumentExeption");
		}catch(Exception ex){
			assertEquals("Eps is negative or zero", ex.getMessage());
		}
	}	
	
	@Test
	public void throwExceptionIfEpsZero(){
		try{
			solver = new Dichotomy.Builder().a(0).b(10).sigma(1.F).eps(0).build();
			fail("expected illegalArgumentExeption");
		}catch(Exception ex){
			assertEquals("Eps is negative or zero", ex.getMessage());
		}
	}	

	
	@Test
	public void getMinusOneIfFunctionIsSqrXminusOne(){
		solver = new Dichotomy.Builder().a(-1).b(2).sigma(0.4F).eps(0.01F).function(new FunctionSqrXminusOne()).build();
		
		float result = solver.findMinimum();

		assertEquals(-1,result,0.1);
	}

	@Test
	public void getZeroIfFunctionIsLnOfXplusOne(){
		solver = new Dichotomy.Builder().a(0).b(1).sigma(0.1F).eps(0.01F).function(new FunctionLnOfXplusOne()).build();
		
		float result = solver.findMinimum();

		assertEquals(0,result,0.1);
	}
}
