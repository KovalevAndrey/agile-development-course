package ru.unn.agile.dichotomy.viewmodel;

import ru.unn.agile.dichotomy.Dichotomy;
import ru.unn.agile.dichotomy.FunctionLnOfXplusOne;
import ru.unn.agile.dichotomy.FunctionSqrXminusOne;

public class ViewModel {
	public String a;
	public String b;	
	public String sigma;
	public String eps;
	public String result;
	public Function function;
	
	public enum Function {
		FunctionLnOfXplusOne("log10(x+1)"),
		FunctionSqrXminusOne("x^2-1");
		
		private final String functionString;
		
		private Function(String functionString) {
			this.functionString = functionString;
		}
		
		public String toString() {
			return functionString;
		}
	};
	
	public ViewModel() {
		this.a = "";
		this.b = "";
		this.sigma = "";
		this.eps = "";
		this.result = "";
		this.function = Function.FunctionLnOfXplusOne;
	}


	public void getResult() {
		float a,b,sigma,eps;
	
		try{
			a = Float.valueOf(this.a);
			b = Float.valueOf(this.b);
			sigma = Float.valueOf(this.sigma);
			eps = Float.valueOf(this.eps);
		} catch(Exception ex) {
			result = "Bad Format";
			return;
		}
		
		Dichotomy dichotomySolver=null;
		float result = 0;
		
		try{
			switch(function) {
				case FunctionLnOfXplusOne:
					dichotomySolver = new Dichotomy.Builder().a(a).b(b).eps(eps).sigma(sigma).function(new FunctionLnOfXplusOne()).build();
					result = dichotomySolver.findMinimum();
				break;
				
				case FunctionSqrXminusOne:
					dichotomySolver = new Dichotomy.Builder().a(a).b(b).eps(eps).sigma(sigma).function(new FunctionSqrXminusOne()).build();
					result = dichotomySolver.findMinimum();
				break;
				
				default:
					this.result = "Error of function";
					return;	
			}
		} catch(Exception ex) {
			this.result = "Data is invalid for algorithm";	
			return;
		}
		dichotomySolver = null;
		this.result = String.valueOf(result);
	}
}
