package com.jeff.fischman.price.service;

import org.junit.Assert;
import org.junit.Test;

public class PriceServiceFactoryTests {
    @Test
    public void testCreateCmePriceService() {
        PriceService cme = create("cme");
        Assert.assertTrue(cme instanceof CmePriceService);
    }

    @Test
    public void testCreateEurexPriceService() {
        PriceService eurex = create("eurex");
        Assert.assertTrue(eurex instanceof EurexPriceService);
    }

    private static PriceService create(String platformStr) {
        String args[] = {String.format("--price.platform=%s", platformStr)};
        PriceService res = PriceServiceFactory.create(args);
        return res;
    }
}
