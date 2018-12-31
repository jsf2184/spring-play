package com.jeff.fischman;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class AppPropertiesUser {
    public static Logger _log = LoggerFactory.getLogger(AppPropertiesUser.class) ;

    AppProperties _appProperties;

    public AppPropertiesUser(AppProperties appProperties) {
        _appProperties = appProperties;
        verifyProperties();

    }

    public void verifyProperties() {
        logProperty("java.vm.version");
        logProperty("HOMEPATH");
        logProperty("price.platform");
    }

    public void logProperty(String propertyName) {
        _log.info(String.format("Property[%s] = %s", propertyName, _appProperties.getProperty(propertyName)));
    }
}
