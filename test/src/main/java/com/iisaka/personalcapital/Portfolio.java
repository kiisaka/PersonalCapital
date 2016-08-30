package com.iisaka.personalcapital;

import java.util.Random;

public class Portfolio implements Cloneable {

	Double expectedReturn;
	Double expectedRisk;
	
	Double currentValue;
	
	private static final Random random = new Random();
	
	public Portfolio(Portfolio anotherPortfolio) {
		this.currentValue = anotherPortfolio.currentValue;
		this.expectedReturn = anotherPortfolio.expectedReturn;
		this.expectedRisk = anotherPortfolio.expectedRisk;
	}
	
	public Portfolio(Double currentValue, Double expectedReturn, Double expectedRisk) {
		this.currentValue = currentValue;
		this.expectedReturn = expectedReturn;
		this.expectedRisk = expectedRisk;
	}
	
	public void computeRandomReturn() {
		Double portfolioReturn = expectedReturn + expectedRisk * random.nextGaussian();
		currentValue *= portfolioReturn + 1.0d;
	}

	public Double getCurrentValue() {
		return currentValue;
	}
}
