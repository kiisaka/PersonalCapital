package com.iisaka.personalCapital;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;

public class InflationAdjustmentFactorTest extends TestCase {

	private PortfolioReturnSimulation simulation;

	@Before
	public void setUp() {
		//
		//	For this purpose, set up a simulation with no portfolio, but set the
		//	initial value to be 100, with an inflation rate of 3.5%
		//
		simulation = new PortfolioReturnSimulation(0, 1, new BigDecimal("100"), 0.0d, 0.0d, 0.035d);
	}

	@Test
	public void test() {
		//
		//	Verify that that, the present value of 103.5 after one period is 100.0
		//
		assert simulation.calculateInflationAdjustedValue(103.5d).doubleValue() == 100.0d;
	}
}
