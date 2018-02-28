package com.pc.montecarlo;

import junit.framework.Assert;
import junit.framework.TestCase;

public class CompoundInvestmentStrategyTest extends TestCase {

    public void testGetAssetValue() {
        //Test with risk = 0.0
        Portfolio folio = new Portfolio(5.0f, 0.0f, 100, Portfolio.PortfolioType.VERY_CONSERVATIVE);
        InvestmentStrategy strategyWithRisk = new CompoundInvestmentStrategy();
        double assetValue = strategyWithRisk.getAssetValue(folio, 0.0f, 1);
        Assert.assertTrue(Double.compare(assetValue, 105.00) >= 0);

        assetValue = strategyWithRisk.getAssetValue(folio,1);
        Assert.assertTrue(Double.compare(assetValue, 105.00) > 0);
    }
}