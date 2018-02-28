package com.pc.montecarlo;

import junit.framework.Assert;
import junit.framework.TestCase;

public class PortfolioTest extends TestCase {

    public void testGetPortfolio() {
        Portfolio folio = new Portfolio(5.0f, 3.0f, 100, Portfolio.PortfolioType.VERY_CONSERVATIVE);
        Assert.assertEquals(Float.compare(folio.getMeanReturn(), 5.0f), 0);
        Assert.assertEquals(Float.compare(folio.getRisk(), 3.0f), 0);
        Assert.assertEquals(Double.compare(folio.getAssetValue(), 100.0), 0);
        Assert.assertEquals(folio.getType(), Portfolio.PortfolioType.VERY_CONSERVATIVE);
    }

    public void testSetPortfolio() {
        Portfolio folio = new Portfolio(5.0f, 3.0f, 100, Portfolio.PortfolioType.VERY_CONSERVATIVE);
        folio.setRisk(9.0f);
        folio.setMeanReturn(10.0f);
        folio.setAssetValue(10000);
        Assert.assertEquals(Float.compare(folio.getMeanReturn(), 10.0f), 0);
        Assert.assertEquals(Float.compare(folio.getRisk(), 9.0f), 0);
        Assert.assertEquals(Double.compare(folio.getAssetValue(), 10000.0), 0);
        Assert.assertEquals(folio.getType(), Portfolio.PortfolioType.VERY_CONSERVATIVE);
    }

}