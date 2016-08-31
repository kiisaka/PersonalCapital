package com.iisaka.personalCapital;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.Before;
import org.junit.Test;

public class PortfolioReturnSimulationTest {

	final static int numPortfolios = 10000;
	final static int numPeriods = 20;
	final static BigDecimal initialValue = new BigDecimal("100000").setScale(2, RoundingMode.HALF_DOWN);
	final static Double inflationRate = 0.035d;
	private PortfolioReturnSimulation aggressivePortfolioSimulation;
	private PortfolioReturnSimulation conservativePortfolioSimulation;
	private PortfolioReturnSimulation inflationTrackingPortfolioSimulation;

	@Before
	public void setup() {
		//
		//	Set up three simulations: aggressive, very conservative, and inflation-tracking portfolio simulations
		
		inflationTrackingPortfolioSimulation = new PortfolioReturnSimulation("Inflation tracking", numPortfolios, numPeriods, initialValue,
				inflationRate, 0.0d, inflationRate);
		aggressivePortfolioSimulation = new PortfolioReturnSimulation("Aggressive", numPortfolios, numPeriods, initialValue,
				0.094324d, 0.15675d, inflationRate);
		conservativePortfolioSimulation = new PortfolioReturnSimulation("Very conservative", numPortfolios, numPeriods, initialValue,
				0.06189d, 0.063438d, inflationRate);
	}

	@Test
	public void runPortfolioSimulations() {
		//
		//	Run simulations of two types of portfolios: aggressive and very conservative
		//
		
		aggressivePortfolioSimulation.run();
		conservativePortfolioSimulation.run();

		showResults(aggressivePortfolioSimulation);
		showResults(conservativePortfolioSimulation);
	}

	@Test
	public void testInflationTrackingPortfolio() {

		//
		//	Run a simulation of portfolios tracking the inflation, with 0 risk.
		//	Verify that the present value of the portfolio is the same as the initial value.
		//
		
		inflationTrackingPortfolioSimulation.run();
		showResults(inflationTrackingPortfolioSimulation);
		assertTrue(Math.abs(inflationTrackingPortfolioSimulation.getInflationAdjustedMedianValue()
				- initialValue.doubleValue()) <= 0.01);
	}

	private void showResults(PortfolioReturnSimulation portfolioSimulation) {

		System.out.println(portfolioSimulation.name + ": Expected annual return = " + portfolioSimulation.expectedReturn
				+ ", Expected annual risk = " + portfolioSimulation.expectedRisk + ": Inflation Adjusted Median value = "
				+ portfolioSimulation.getInflationAdjustedMedianValue() + ", Inflation Adjusted Best 10% value = "
				+ portfolioSimulation.getInflationAdjustedTop10PercentileValue() + ", Inflation Adjusted Worst 10% value = "
				+ portfolioSimulation.getInflationAdjustedBottom10PercentileValue());

	}

}
