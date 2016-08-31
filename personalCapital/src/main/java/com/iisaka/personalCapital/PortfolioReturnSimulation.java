package com.iisaka.personalCapital;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.stat.StatUtils;

//	A simulation represents an experiment.
//
//	A simulation involves a set of portfolios, and parameters applied to the portfolios
//

public class PortfolioReturnSimulation {

	protected List<Portfolio> portfolios;
	protected double[] portfolioValues;

	protected BigDecimal initialValue;
	protected Double expectedReturn;
	protected Double expectedRisk;
	protected Double inflationRate;

	protected int numPeriods;
	protected Double inflationAdjustmentFactor;

	public PortfolioReturnSimulation(int numPortfolios, int numPeriods, BigDecimal initialValue, Double expectedReturn,
			Double expectedRisk, Double inflationRate) {

		portfolios = new ArrayList<>(numPortfolios);
		portfolioValues = new double[numPortfolios];

		this.numPeriods = numPeriods;
		this.initialValue = initialValue;
		this.expectedReturn = expectedReturn;
		this.expectedRisk = expectedRisk;
		this.inflationRate = inflationRate;

		for (int i = 0; i < numPortfolios; i++) {
			portfolios.add(new Portfolio(initialValue, expectedReturn, expectedRisk));
		}
	}

	//
	// Run the experiment.
	// Random returns are applied to each of the portfolios x times,
	// where x = number of periods (year)
	//

	public void run() {

		for (int i = 0; i < numPeriods; i++) {
			for (Portfolio portfolio : portfolios) {
				portfolio.computeRandomReturn();
			}
		}

		for (int i = 0; i < portfolios.size(); i++) {
			portfolioValues[i] = portfolios.get(i).getCurrentValue().doubleValue();
		}
	}

	//
	// Compute the median, top 10%, bottom 10% portfolio values
	//

	public Double getMedianValue() {
		return StatUtils.percentile(portfolioValues, 50);
	}

	public Double getTop10PercentileValue() {
		return StatUtils.percentile(portfolioValues, 90);
	}

	public Double getBottom10PercentileValue() {
		return StatUtils.percentile(portfolioValues, 10);
	}

	//
	// Get the inflation-adjusted portfolio values
	//

	public Double getInflationAdjustedMedianValue() {
		return calculateInflatationAdjustedValue(getMedianValue());
	}

	public Double getInflationAdjustedTop10PercentileValue() {
		return calculateInflatationAdjustedValue(getTop10PercentileValue());
	}

	public Double getInflationAdjustedBottom10PercentileValue() {
		return calculateInflatationAdjustedValue(getBottom10PercentileValue());
	}

	//
	// Adjust the portfolio value by the inflation adjustment factor
	//

	Double calculateInflatationAdjustedValue(Double portfolioValue) {
		return portfolioValue / getInflationAdjustmentFactor();
	}

	//
	// Compute the inflation adjustment factor if necessary
	//

	private Double getInflationAdjustmentFactor() {
		if (inflationAdjustmentFactor == null) {
			inflationAdjustmentFactor = Math.pow(1.0d + inflationRate, numPeriods);
		}
		return inflationAdjustmentFactor;
	}
}
