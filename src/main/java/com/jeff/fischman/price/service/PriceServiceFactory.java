package com.jeff.fischman.price.service;

import com.jeff.fischman.Platform;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.SimpleCommandLinePropertySource;

public class PriceServiceFactory {

    public static Logger _log = LoggerFactory.getLogger(PriceServiceFactory.class) ;

    public static PriceService create(String[] args) {
        SimpleCommandLinePropertySource propertySource = new SimpleCommandLinePropertySource(args);
        String platformProperty = propertySource.getProperty("price.platform");
        Platform platform = Platform.valueOf(platformProperty);
        PriceService res = create(platform.getConfigClass());
        return res;
    }

    public static PriceService create(Class<?> configClass) {
        _log.info("PriceServiceFactory.create(): entered");
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(configClass);
        PriceService res = context.getBean(PriceService.class);
        _log.info("PriceServiceFactory.create(): completed");
        return res;
    }
}
