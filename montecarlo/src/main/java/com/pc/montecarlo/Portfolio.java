package com.pc.montecarlo;

/**
 * The portfolio class that holds the portfolio and the balance.
 */
public class Portfolio {

    public enum PortfolioType {
        AGGRESSIVE, VERY_CONSERVATIVE;
    }

    private float meanReturn = 0.0f;
    private float risk = 0.0f;
    private double assetValue = 0.0;
    private PortfolioType type;

    /**
     * Creates a portfolio with asset, average return and risk.
     *
     *
     * @param meanReturn The average annual return.
     * @param risk The risk percentage
     * @param assetValue The total asset value.
     */
    public Portfolio(float meanReturn, float risk, double assetValue, PortfolioType type) {
        this.meanReturn = meanReturn;
        this.risk = risk;
        this.assetValue = assetValue;
        this.type = type;
    }

    /**
     * Construct the portfolio based on type.
     * @param type
     */
    public Portfolio(PortfolioType type) {
        this.type = type;
    }

    /**
     * @return the mean return
     */
    public float getMeanReturn() {
        return meanReturn;
    }

    /**
     * @return the risk
     */
    public float getRisk() {
        return risk;
    }

    /**
     * @return the current asset value.
     */
    public double getAssetValue() {
        return assetValue;
    }

    /**
     * @return the type of the portfolio
     */
    public PortfolioType getType() {
        return type;
    }

    /**
     * Setter for setting the mean return.
     * @param meanReturn the mean return
     */
    public void setMeanReturn(float meanReturn) {
        this.meanReturn = meanReturn;
    }

    /**
     * Setter to set the risk.
     * @param risk the risk percentage.
     */
    public void setRisk(float risk) {
        this.risk = risk;
    }

    /**
     * Setter for the asset value
     * @param assetValue the asset value to set.
     */
    public void setAssetValue(double assetValue) {
        this.assetValue = assetValue;
    }
}
