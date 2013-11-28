package ru.unn.agile.dichotomy.viewmodel;


import java.util.ArrayList;

import ru.unn.agile.dichotomy.Dichotomy;
import ru.unn.agile.dichotomy.FunctionLnOfXplusOne;
import ru.unn.agile.dichotomy.FunctionSqrXminusOne;

public class ViewModel {
	private String a;
	private String b;	
	private String sigma;
	private String eps;
	private String resultMessage;
	private Function function;
	private ILogger logger;
	
	public String getA() {
		return a;
	}
	public void setA(String a) {
		this.a = a;
	}

	public String getB() {
		return b;
	}
	public void setB(String b) {
		this.b = b;
	}

	public String getSigma() {
		return sigma;
	}
	public void setSigma(String sigma) {
		this.sigma = sigma;
	}

	public String getEps() {
		return eps;
	}
	public void setEps(String eps) {
		this.eps = eps;
	}

	public Function getFunction() {
		return function;
	}
	public void setFunction(Function function) {
		this.function = function;
	}
		
	public String getResultMessage() {
		return resultMessage;
	}
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
	
	public static class Builder {
		private String a;
		private String b;
		private String sigma;
		private String eps;
		private Function function;
		private ILogger logger;
		
		public Builder a(String a) {
			this.a = a;
			return this;
		}
		
		public Builder b(String b) {
			this.b = b;
			return this;
		}
		
		public Builder eps(String eps) {
			this.eps = eps;
			return this;
		}
		
		public Builder sigma(String sigma) {
			this.sigma = sigma;
			return this;
		}
		
		public Builder function(Function function) {
			this.function = function;
			return this;
		}
		
		public Builder logger(ILogger logger) {
			this.logger = logger;
			return this;
		}
		
		public ViewModel build() {
			return new ViewModel(this);
		}
	}
	
	public ViewModel(Builder builder) {
		this.a = builder.a;
		this.b = builder.b;
		this.sigma = builder.sigma;
		this.eps = builder.eps;
		this.resultMessage = null;
		if (builder.function==null)
			this.function = ViewModel.Function.FunctionLnOfXplusOne;
		else
			this.function = builder.function;
		this.logger = builder.logger;
	}
	
	public int getLogSize() {
		return this.logger.getLogSize();
	}
	
	public void getResult() {
		float a,b,sigma,eps;
		
		if (this.logger == null)
		{
			resultMessage = "Logger has null pointer";
			return;
		}
		try{
			a = Float.valueOf(this.a);
			b = Float.valueOf(this.b);
			sigma = Float.valueOf(this.sigma);
			eps = Float.valueOf(this.eps);
		} catch(Exception ex) {
			resultMessage = "Bad Format";
			this.logger.addRecord(createLogRecord());
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
					this.resultMessage = "Error of function";
					return;	
			}
		} catch(Exception ex) {
			this.resultMessage = "Data is invalid for algorithm";	
			return;
		}
		dichotomySolver = null;
		this.resultMessage = String.valueOf(result);
		this.logger.addRecord(createLogRecord());
	}
	
	private String createLogRecord(){
		
		return "a = " + a + 
				"; b = " + b +
				"; eps = " + eps +
				"; sigma = " + sigma +
				"; function is " + function +
				"; resultMessage = " + resultMessage;
	}

	public ArrayList<String> getLog() {
		return this.logger.getLogList();
	}
	
}
