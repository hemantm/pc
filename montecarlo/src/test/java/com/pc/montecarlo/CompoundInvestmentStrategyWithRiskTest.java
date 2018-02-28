package com.pc.montecarlo;

import junit.framework.Assert;
import junit.framework.TestCase;

public class CompoundInvestmentStrategyWithRiskTest extends TestCase {

    public void testGetAssetValue() {
        //Test with risk = 0.0
        Portfolio folio = new Portfolio(5.0f, 0.0f, 100000, Portfolio.PortfolioType.VERY_CONSERVATIVE);
        InvestmentStrategy strategyWithRisk = new CompoundInvestmentStrategyWithRisk();
        double assetValue = strategyWithRisk.getAssetValue(folio, 2.0f, 1);
        Assert.assertTrue(Double.compare(assetValue, folio.getAssetValue()) > 0);

        assetValue = strategyWithRisk.getAssetValue(folio,1);
        Assert.assertTrue(Double.compare(assetValue, folio.getAssetValue()) > 0);
    }
}