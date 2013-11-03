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
	
	public static class Builder{
		private String a;
		private String b;
		private String sigma;
		private String eps;
		private Function function;
		
		public Builder a(String a){
			this.a = a;
			return this;
		}
		
		public Builder b(String b){
			this.b = b;
			return this;
		}
		
		public Builder eps(String eps){
			this.eps = eps;
			return this;
		}
		
		public Builder sigma(String sigma){
			this.sigma = sigma;
			return this;
		}
		
		public Builder function(Function function){
			this.function = function;
			return this;
		}
		
		public ViewModel build(){
			return new ViewModel(this);
		}

	}
	
	public enum Function{
		FunctionLnOfXplusOne("log10(x+1)"),
		FunctionSqrXminusOne("x^2-1");
		
		private final String functionString;
		private Function(String functionString){
			this.functionString = functionString;
		}
		public String toString(){
			return functionString;
		}
	};
	
	public ViewModel(){
		this.a = "";
		this.b = "";
		this.sigma = "";
		this.eps = "";
		this.result = "";
		this.function = Function.FunctionLnOfXplusOne;
	}

	public ViewModel(Builder builder) {
		this.a = builder.a;
		this.b = builder.b;		
		this.sigma = builder.sigma;
		this.eps = builder.eps;
		this.function = builder.function;
	}

	public void getResult() {
		float a,b,sigma,eps;
	
		try{
			a = Float.valueOf(this.a);
			b = Float.valueOf(this.b);
			sigma = Float.valueOf(this.sigma);
			eps = Float.valueOf(this.eps);
		}catch(Exception ex){
			result = "Bad Format";
			return;
		}
		
		Dichotomy dichotomySolver=null;
		float result = 0;
		
		switch(function){
			case FunctionLnOfXplusOne:
				try{
					dichotomySolver = new Dichotomy.Builder().a(a).b(b).eps(eps).sigma(sigma).function(new FunctionLnOfXplusOne()).build();
				}catch(Exception ex){
					this.result = "Data is invalid for alghorithm";
					return;
				}
				result = dichotomySolver.findMinimum();
				break;
			case FunctionSqrXminusOne:
				try{
					dichotomySolver = new Dichotomy.Builder().a(a).b(b).eps(eps).sigma(sigma).function(new FunctionSqrXminusOne()).build();
				}catch(Exception ex){
					this.result = "Data is invalid for alghorithm";	
					return;
				}
				result = dichotomySolver.findMinimum();
				break;
			default:
				break;		
		}
		dichotomySolver = null;
		this.result = String.valueOf(result);
	}
	
}
