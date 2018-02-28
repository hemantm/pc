package com.pc.montecarlo;

/**
 * The investment strategy to use for computing returns.
 */
public interface InvestmentStrategy {

    /**
     * Given the portfolio and inflation compute the new balance. Takes inflation in to account.
     * @param folio The portfolio to use.
     * @param inflation the inflation factor to use.
     * @param timeInterval The number of time period over which to compute the new asset value.
     * @return the new asset value.
     */
    public double getAssetValue(Portfolio folio, float inflation, int timeInterval);

    /**
     * Given the portfolio compute the new balance for a period. Does not take inflation in to account.
     * @param folio The portfolio to use.
     * @param timeInterval The number of time period over which to compute the new asset value.
     * @return the new asset value.
     */
    public double getAssetValue(Portfolio folio, int timeInterval);
}
