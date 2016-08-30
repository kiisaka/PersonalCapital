package com.iisaka.personalcapital;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class PortfolioReturnTest {

	static final Double initialPortfolioValue = 100000.0d;
	static final Double inflationRate = 0.035d;
	static final int periods = 20;
	static final int numSimulations = 10000;
	
	static final Portfolio aggressivePortfolio = new Portfolio(initialPortfolioValue, 0.094324d, 0.15675d);
	static final Portfolio veryConservativePortfolio = new Portfolio(initialPortfolioValue, 0.06189d, 0.063438d);
	
	final List<Portfolio> aggressivePortfolios = new ArrayList<>(numSimulations);
	final List<Portfolio> veryConservativePortfolios = new ArrayList<>(numSimulations);

	@Before
	public void setUp() throws Exception {
		 for (int i=0; i<numSimulations; i++) {
			 aggressivePortfolios.add(new Portfolio(aggressivePortfolio));
			 veryConservativePortfolios.add(new Portfolio(veryConservativePortfolio));
		 }
	}

	@Test
	public void simulateReturns() {
		for (int i=0; i<periods; i++) {
			for (Portfolio portfolio: aggressivePortfolios) {
				portfolio.computeRandomReturn();
			}
			for (Portfolio portfolio: veryConservativePortfolios) {
				portfolio.computeRandomReturn();
			}
		}
		
		Double inflationAdjustmentFactor = Math.pow(1.0d + inflationRate, periods);
	}

}
