package com.pc.montecarlo;

import junit.framework.Assert;
import junit.framework.TestCase;

public class SimulatorTest extends TestCase {

    public void testSimulate() {
        PortfolioFactory portfolioFactory = new PortfolioFactory();
        Portfolio aggressive = portfolioFactory.getPortfolio(Portfolio.PortfolioType.AGGRESSIVE);
        aggressive.setAssetValue(100000);
        aggressive.setMeanReturn(9.4324f);
        aggressive.setRisk(15.675f);
        Simulator monteCarloSimulator = new Simulator(new CompoundInvestmentStrategyWithRisk(), 3.5f);
        Simulator.MonteCarloResult result = monteCarloSimulator.simulate(aggressive, 20, 10000);
        Assert.assertNotNull(result);
    }
}