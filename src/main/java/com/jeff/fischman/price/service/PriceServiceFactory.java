package com.jeff.fischman.price.service;

import com.jeff.fischman.Platform;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.SimpleCommandLinePropertySource;

public class PriceServiceFactory {

    public static Logger _log = LoggerFactory.getLogger(PriceServiceFactory.class) ;

    public static PriceService create(String[] args) {
        // We get which type of price.platform from the command line and use SimpleCommandLinePropertySource
        // to help us extract the value of that property.
        //
        SimpleCommandLinePropertySource propertySource = new SimpleCommandLinePropertySource(args);
        String platformProperty = propertySource.getProperty("price.platform");
        // turn the property into an enum
        Platform platform = Platform.valueOf(platformProperty);
        // the enum knows the appropriate config class for that enum.
        Class<?> configClass = platform.getConfigClass();
        PriceService res = create(configClass);
        return res;
    }

    @SuppressWarnings("WeakerAccess")
    public static PriceService create(Class<?> configClass) {
        _log.info("PriceServiceFactory.create(): entered");
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(configClass);
        PriceService res = context.getBean(PriceService.class);
        _log.info("PriceServiceFactory.create(): completed");
        return res;
    }
}
