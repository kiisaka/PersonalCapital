package com.iisaka.personalcapital;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

public class GaussianTest {

	private Random random;

	@Before
	public void setUp() throws Exception {
		random = new Random();
	}

	@Test
	public void test() {
		for (int i=0; i<20; i++) {
			System.out.println(random.nextGaussian());
		}
	}

}
