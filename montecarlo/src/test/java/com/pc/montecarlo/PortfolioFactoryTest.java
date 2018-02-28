package com.pc.montecarlo;

import junit.framework.Assert;
import junit.framework.TestCase;
import org.omg.PortableServer.POA;

public class PortfolioFactoryTest extends TestCase {

    public void testGetPortfolio() {
        PortfolioFactory portfolioFactory = new PortfolioFactory();
        Portfolio folio = portfolioFactory.getPortfolio(Portfolio.PortfolioType.VERY_CONSERVATIVE);
        Assert.assertEquals(folio.getType(), Portfolio.PortfolioType.VERY_CONSERVATIVE);

        folio = portfolioFactory.getPortfolio(Portfolio.PortfolioType.AGGRESSIVE);
        Assert.assertEquals(folio.getType(), Portfolio.PortfolioType.AGGRESSIVE);
    }
}