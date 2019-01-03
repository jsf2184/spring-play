package com.jeff.fischman.price.service.config;

import com.jeff.fischman.price.service.CmePriceService;
import com.jeff.fischman.price.service.EurexPriceService;
import com.jeff.fischman.price.service.PriceService;
import com.jeff.fischman.utility.TestUtility;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConditionalConfigTests {
    @Test
    public void testContextCreatesEurexPriceService() {
        PriceService priceService = create("eurex");
        Assert.assertTrue(priceService instanceof EurexPriceService);
    }

    @Test
    public void testContextCreatesCmePriceService() {
        PriceService priceService = create("cme");
        Assert.assertTrue(priceService instanceof CmePriceService);
    }


    private PriceService create(String platformName) {
        AnnotationConfigApplicationContext context = TestUtility.wireContext(ConditionalConfig.class,
                                                                             "price.platform",
                                                                             platformName,
                                                                             null);
        PriceService res = (PriceService) context.getBean("priceService");
        return res;
    }
}
