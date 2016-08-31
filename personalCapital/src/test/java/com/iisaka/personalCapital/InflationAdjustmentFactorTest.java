package com.iisaka.personalCapital;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;

public class InflationAdjustmentFactorTest extends TestCase {

	private PortfolioReturnSimulation simulation;

	@Before
	public void setUp() {
		simulation = new PortfolioReturnSimulation(0, 1, new BigDecimal("100"), 0.0d, 0.0d, 0.035d);
	}

	@Test
	public void test() {
		assert (simulation.calculateInflatationAdjustedValue(0.035d) == 100.0d);
	}
}
