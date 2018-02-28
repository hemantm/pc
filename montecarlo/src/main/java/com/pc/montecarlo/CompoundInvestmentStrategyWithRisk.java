package com.pc.montecarlo;

import java.util.Random;

/**
 * Provides a compound investment strategy with risk management. The risk every period is randomized over two standard
 * deviation.
 *
 */
public class CompoundInvestmentStrategyWithRisk  implements InvestmentStrategy {

    private Random randomGenerator = new Random();

    /**
     * Generate the variance given the risk using a random gaussian value.
     *
     * @param risk the given portfolio risk.
     * @return the risk variance
     */
    private double generateVariance(float risk) {
        boolean goodValue = false;
        double variance = 0.0;
        while (!goodValue) {
            variance = randomGenerator.nextGaussian();
            if ((Double.compare(variance, 2.0) < 0) && (Double.compare(variance, -2.0) > 0))
                goodValue = true;
        }
        return variance * risk;
    }

    /**
     * Get the asset value for the portfolio after specified number of time periods
     *
     * @param folio The portfolio to use.
     * @param inflation the inflation factor to use.
     * @return the new asset value for the portfolio after multiple intervals.
     */
    public double getAssetValue(Portfolio folio, float inflation, int timeInterval) {
        double assetValue = folio.getAssetValue();
        for (int i = 0; i < timeInterval; i++) {
            double expectedReturn = folio.getMeanReturn()/100 + generateVariance(folio.getRisk()/100) - inflation/100;
            assetValue = assetValue  + assetValue * expectedReturn;
        }
        return assetValue;
    }

    /**
     * Get the asset value for the portfolio after specified number of time periods. Does not take inflation in to account.
     * @param folio The portfolio to use.
     * @param timeInterval The number of time period over which to compute the new asset value.
     * @return the new asset value.
     */
    public double getAssetValue(Portfolio folio, int timeInterval) {
        return getAssetValue(folio, 0, timeInterval);
    }
}
