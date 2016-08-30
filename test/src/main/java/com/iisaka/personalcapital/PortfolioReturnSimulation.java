package com.iisaka.personalcapital;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.stat.StatUtils;

public class PortfolioReturnSimulation {

	final int numPeriods;
	final Double initialValue;
	final Double expectedReturn;
	final Double expectedRisk;
	final Double inflationRate;

	final List<Portfolio> portfolios;
	final double[] returns;
	Double inflationAdjustmentFactor;
	
	public PortfolioReturnSimulation(int numPortfolios, int numPeriods, Double initialValue, Double expectedReturn, Double expectedRisk, Double inflationRate) {
		portfolios = new ArrayList<>(numPortfolios);
		returns = new double[numPortfolios];
		
		this.numPeriods = numPeriods;
		this.initialValue = initialValue;
		this.expectedReturn = expectedReturn;
		this.expectedRisk = expectedRisk;
		this.inflationRate = inflationRate ;
		
		for (int i = 0; i < numPortfolios; i++) {
			portfolios.add(new Portfolio(initialValue, expectedReturn, expectedRisk));
		}
	}
	
	public void run() {
		
		for (int i =0; i < numPeriods; i++) {
			for (Portfolio portfolio: portfolios) {
				portfolio.computeRandomReturn();
			}
		}
		
		for (int i =0; i < portfolios.size(); i++) {
			returns[i] = portfolios.get(i).getCurrentValue() / initialValue - 1.0d;
		}
	}
	
	public double getMedianReturn() {
		return StatUtils.percentile(returns,  0.5d);
	}
	
	public double top10PercentReturn() {
		return StatUtils.percentile(returns,  0.9d);
	}

	public double bottom10PercentReturn() {
		return StatUtils.percentile(returns,  0.1d);
	}

	public double calculateInflationAdjustedReturn(double portfolioReturn) {
		return ((portfolioReturn + 1.0d) / getInflationAdjustmentFactor()) - 1.0d;
	}
	
	public double getInflationAdjustmentFactor() {
		if (inflationAdjustmentFactor == null) {
			this.inflationAdjustmentFactor = Math.pow(1.0d + inflationRate,  numPeriods);
		}
		return inflationAdjustmentFactor;
	}
	
}
