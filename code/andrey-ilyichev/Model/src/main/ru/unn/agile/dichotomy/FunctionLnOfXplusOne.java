package ru.unn.agile.dichotomy;

public class FunctionLnOfXplusOne implements IFunction {
	
	public float getValue(float x) {
		return (float) (Math.log10(x + 1));
	}
}
