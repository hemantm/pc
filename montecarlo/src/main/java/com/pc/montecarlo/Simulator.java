package com.pc.montecarlo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The monte carlo simulator that takes a strategy and uses that to run simulations.
 */
public class Simulator {
    public class MonteCarloResult {
        //The ten percentile value
        public double worstCase;
        //The median value
        public double median;
        //The ninety percentile value
        public double bestCase;
    }

    private InvestmentStrategy strategy;
    private float inflation = 0.0f;

    /**
     * default constructor that takes a strategy and inflation.
     * @param strategy the investment strategy
     * @param inflation the inflation
     */
    public Simulator(InvestmentStrategy strategy, float inflation) {
        this.strategy = strategy;
        this.inflation = inflation;
    }

    /**
     * Simulate the different scenarios and capture them in the array.
     * @param folio The port folio to use for simulations.
     * @param timePeriod The time period for which the asset value is to be computed.
     * @param numSimulations The number of simulations to run.
     * @return the result for the simulation
     */
    public MonteCarloResult simulate(Portfolio folio, int timePeriod, int numSimulations) {
        List<Double> results = new ArrayList<>();
        for (int i = 0; i < numSimulations; i++) {
            results.add(strategy.getAssetValue(folio, inflation, timePeriod));
        }
        Collections.sort(results);
        int tenPercentile = results.size() / 10;
        int fifetyPercentile = results.size() / 2;
        int ninetyPercentile = results.size() - (results.size() /10);

        MonteCarloResult result = new MonteCarloResult();
        result.bestCase = results.get(ninetyPercentile);
        result.median = results.get(fifetyPercentile);
        result.worstCase = results.get(tenPercentile);
        return result;
    }

    public static void main(String[] args) {
        PortfolioFactory portfolioFactory = new PortfolioFactory();
        Portfolio aggressive = portfolioFactory.getPortfolio(Portfolio.PortfolioType.AGGRESSIVE);
        aggressive.setAssetValue(100000);
        aggressive.setMeanReturn(9.4324f);
        aggressive.setRisk(15.675f);
        Simulator monteCarloSimulator = new Simulator(new CompoundInvestmentStrategyWithRisk(), 3.5f);
        MonteCarloResult result = monteCarloSimulator.simulate(aggressive, 20, 10000);
        System.out.println("Portfolio :" + aggressive.getType());
        System.out.println(String.format("10 Percent Worst case performance=%.2f",result.worstCase));
        System.out.println(String.format("Median performance=%.2f",result.median));
        System.out.println(String.format("10 Percent Best case performance=%.2f",result.bestCase));

        Portfolio veryConservative = portfolioFactory.getPortfolio(Portfolio.PortfolioType.VERY_CONSERVATIVE);
        veryConservative.setAssetValue(100000);
        veryConservative.setMeanReturn(6.189f);
        veryConservative.setRisk(6.3438f);
        monteCarloSimulator = new Simulator(new CompoundInvestmentStrategyWithRisk(), 3.5f);
        result = monteCarloSimulator.simulate(veryConservative, 20, 10000);
        System.out.println("Portfolio :" + veryConservative.getType());
        System.out.println(String.format("10 Percent Worst case performance=%.2f",result.worstCase));
        System.out.println(String.format("Median performance=%.2f",result.median));
        System.out.println(String.format("10 Percent Best case performance=%.2f",result.bestCase));
    }

}
