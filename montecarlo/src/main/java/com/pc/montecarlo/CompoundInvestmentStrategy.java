package com.pc.montecarlo;

/**
 * Provides a compound investment strategy. Simply computes the returns over the assets in the portfolio without taking
 * into account the risk.
 */
public class CompoundInvestmentStrategy implements InvestmentStrategy {

    /**
     * Get the asset value for the portfolio
     *
     * @param folio The portfolio to use.
     * @param inflation the inflation factor to use.
     * @return the new asset value for the portfolio after multiple intervals.
     */
    public double getAssetValue(Portfolio folio, float inflation, int timeInterval) {
        double assetValue = folio.getAssetValue();
        for (int i = 0; i < timeInterval; i++) {
            float expectedReturn = folio.getMeanReturn()/100 - inflation/100;
            assetValue = assetValue + assetValue * expectedReturn;
        }
        return assetValue;
    }

    /**
     * Given the portfolio compute the new balance for a period. Does not take inflation in to account.
     * @param folio The portfolio to use.
     * @param timeInterval The number of time period over which to compute the new asset value.
     * @return the new asset value.
     */
    public double getAssetValue(Portfolio folio, int timeInterval) {
        return getAssetValue(folio, 0, timeInterval);
    }
}
