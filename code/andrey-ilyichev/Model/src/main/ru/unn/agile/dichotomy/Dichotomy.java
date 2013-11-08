package ru.unn.agile.dichotomy;

public class Dichotomy {
	private float a;
	private float b;
	private float sigma;
	private float eps;
	private IFunction function;
	
	public static class Builder {
		private float a;
		private float b;
		private float sigma;
		private float eps;
		private IFunction function;
		
		public Builder a(float a) {
			this.a = a;
			return this;
		}
		
		public Builder b(float b) {
			this.b = b;
			return this;
		}
		
		public Builder eps(float eps) {
			this.eps = eps;
			return this;
		}
		
		public Builder sigma(float sigma) {
			this.sigma = sigma;
			return this;
		}
		
		public Builder function(IFunction function) {
			this.function = function;
			return this;
		}
		
		public Dichotomy build() {
			return new Dichotomy(this);
		}

	}
	
	public Dichotomy(Builder builder)
	{
		if (builder.a>builder.b)
		{
			throw new IllegalArgumentException("A more than B");
		}
		this.a = builder.a;
		this.b = builder.b;		
		
		if ((builder.sigma<=0)||(builder.sigma>=(builder.b-builder.a)/2))
		{
			throw new IllegalArgumentException("Sigma is incorrect");
		}
		this.sigma = builder.sigma;
		
		if (builder.eps <= 0)
		{
			throw new IllegalArgumentException("Eps is negative or zero");
		}

		this.eps = builder.eps;
		
		this.function = builder.function;
	}

	private float getCurrentSigma(float a, float b) {
		float ratio = this.sigma / (this.b - this.a);
		return (b-a)*ratio;
	}
	
	public float findMinimum() {
		float currentA = this.a, currentB = this.b;
		do{
			float currentSigma = this.getCurrentSigma(currentA, currentB);
			float x1 = (currentA+currentB-2*currentSigma)/2;
			float x2 = (currentA+currentB+2*currentSigma)/2;
			
			if (this.function.getValue(x1)>this.function.getValue(x2)) {
				currentA = x1;
			}
			else {
				currentB = x2;
			}
		} while(2*this.eps<(currentB - currentA));
		
		float middlePoint = (currentA + currentB)/2;
		return this.function.getValue(middlePoint);
	}
}
