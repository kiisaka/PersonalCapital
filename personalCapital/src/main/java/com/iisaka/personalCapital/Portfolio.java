package com.iisaka.personalCapital;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

public class Portfolio {

	static final Random random = new Random();

	// In an financial application, computing the currency value of assets, it
	// is often necessary to use the BigDecimal
	// to ensure there is no floating rounding error, and to adhere to rounding
	// rules as imposed by regulatory bodies.
	// For this exercise, portfolio values are rounded to the nearest cent after
	// each computation.

	BigDecimal currentValue;
	Double expectedReturn;
	Double expectedRisk;

	public Portfolio(BigDecimal currentValue, Double expectedReturn, Double expectedRisk) {

		this.currentValue = currentValue;
		this.expectedReturn = expectedReturn;
		this.expectedRisk = expectedRisk;
	}

	//	Compute the value of the portfolio, applying a random return, given the
	//	expected return and the expected risk
	//
	//	Use the nextGaussian() function to compute the random return
	//	Round to the nearest cent: the rounding rule depends on the currency and
	//	the regulatory bodies governing the portfolio

	public void computeRandomReturn() {
		currentValue = currentValue
				.multiply(new BigDecimal(1.0d + expectedReturn + expectedRisk * random.nextGaussian()))
				.setScale(2, RoundingMode.HALF_DOWN);
	}

	public BigDecimal getCurrentValue() {
		return currentValue;
	}
}
