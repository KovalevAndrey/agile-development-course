package ru.unn.agile.calculating;

public class StringCalcViewModel {

	public String status;
	public String expression;
	public String result;

	public StringCalcViewModel() {
		status = "Type expression and click \"Calculate\"";
		expression = "";
		result = "";
	}

	public void processCalculate() {
		try {
			double res = StringCalculator.calculate(expression);
			result = String.valueOf(res);
			status = "Success";
		} catch (ArithmeticException e) {
			status = e.getMessage();
			result = "";
		} catch (IllegalArgumentException e) {
			status = e.getMessage();
			result = "";
		}
	}
}
