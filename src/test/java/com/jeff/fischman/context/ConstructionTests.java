package com.jeff.fischman.context;

import com.jeff.fischman.price.service.CmePriceService;
import com.jeff.fischman.price.service.EurexPriceService;
import com.jeff.fischman.price.service.PriceService;
import com.jeff.fischman.price.service.config.cme.CmeConfig;
import com.jeff.fischman.price.service.config.eurex.EurexConfig;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConstructionTests {

    @Test
    public void testIfAnnotationConfigApplicationContextHasBeanFactory() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
        Assert.assertNotNull(beanFactory);
    }

    @Test
    public void testNewContextCreatesConfigClassInstanceWithContextBeanFactory() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(EurexConfig.class);
        EurexConfig eurexConfig = context.getBean(EurexConfig.class);
        Assert.assertNotNull(eurexConfig.getBeanFactory());
        Assert.assertSame(eurexConfig.getBeanFactory(), context.getBeanFactory());
    }

    @Test
    public void testEurexPriceServiceCreationWithEurexConfig() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(EurexConfig.class);
        PriceService priceService = context.getBean(PriceService.class);
        Assert.assertNotNull(priceService);
        Assert.assertEquals("Eurex", priceService.getDependencyName());
        Assert.assertEquals(EurexPriceService.class, priceService.getClass());

        // this should work since there is a bean method named: eurexPriceService
        getPriceServiceByName(context, "eurexPriceService", true);
        // this should not work since there is not a bean method named: priceService
        getPriceServiceByName(context, "priceService", false);

    }

    @Test
    public void testCmePriceServiceCreationWithCmeConfig() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(CmeConfig.class);
        PriceService priceService = context.getBean(PriceService.class);
        Assert.assertNotNull(priceService);
        Assert.assertEquals("Cme", priceService.getDependencyName());
        Assert.assertEquals(CmePriceService.class, priceService.getClass());

        // this should work since there is a bean method named: cmePriceService
        getPriceServiceByName(context, "cmePriceService", true);
        // this should not work since there is not a bean method named: priceService
        getPriceServiceByName(context, "priceService", false);
    }

    void getPriceServiceByName(AnnotationConfigApplicationContext context, String name, boolean expectSuccess) {
        boolean success = false;
        try {
            PriceService res = (PriceService) context.getBean(name);
            success = true;
            Assert.assertNotNull(res);
        } catch (Exception ignore) {

        }
        Assert.assertEquals(expectSuccess, success);
    }

}
