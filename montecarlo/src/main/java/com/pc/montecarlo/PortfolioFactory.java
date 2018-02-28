package com.pc.montecarlo;

/**
 * Generates a portfolio based on the type
 */
public class PortfolioFactory {

    /**
     * Generates an empty portfolio and returns it.
     * @param type The type of the portfolio.
     * @return The created portfolio.
     */
    public Portfolio getPortfolio(Portfolio.PortfolioType type) {
        if (type.equals(Portfolio.PortfolioType.AGGRESSIVE)) {
            return new Portfolio(Portfolio.PortfolioType.AGGRESSIVE);
        } else if (type.equals(Portfolio.PortfolioType.VERY_CONSERVATIVE)) {
            return new Portfolio(Portfolio.PortfolioType.VERY_CONSERVATIVE);
        }
        return null;
    }
}
