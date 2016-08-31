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
		inflationTrackingPortfolioSimulation = new PortfolioReturnSimulation(numPortfolios, numPeriods, initialValue,
				inflationRate, 0.0d, inflationRate);
		aggressivePortfolioSimulation = new PortfolioReturnSimulation(numPortfolios, numPeriods, initialValue,
				0.094324d, 0.15675d, inflationRate);
		conservativePortfolioSimulation = new PortfolioReturnSimulation(numPortfolios, numPeriods, initialValue,
				0.06189d, 0.063438d, inflationRate);
	}

	@Test
	public void runPortfolioSimulations() {
		aggressivePortfolioSimulation.run();
		conservativePortfolioSimulation.run();

		showResults();
	}

	@Test
	public void testInflationTrackingPortfolio() {

		inflationTrackingPortfolioSimulation.run();
		showResults(inflationTrackingPortfolioSimulation);
		assertTrue(Math.abs(inflationTrackingPortfolioSimulation.getInflationAdjustedMedianValue()
				- initialValue.doubleValue()) < 0.01);
	}

	private void showResults() {

		showResults(aggressivePortfolioSimulation);
		showResults(conservativePortfolioSimulation);

	}

	private void showResults(PortfolioReturnSimulation portfolioSimulation) {

		System.out.println("Expected annual return = " + portfolioSimulation.expectedReturn
				+ ", Expected annual risk = " + portfolioSimulation.expectedRisk + ": Median value = "
				+ portfolioSimulation.getInflationAdjustedMedianValue() + ", Best 10% value = "
				+ portfolioSimulation.getInflationAdjustedTop10PercentileValue() + ", Worst 10% value = "
				+ portfolioSimulation.getInflationAdjustedBottom10PercentileValue());

	}

}
