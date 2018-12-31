package com.jeff.fischman.price.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EurexPriceService extends PriceService {
    public static Logger _log = LoggerFactory.getLogger(EurexPriceService.class) ;

    public EurexPriceService(PlatformDependency dependency) {
        super("Eurex", dependency);
    }

    @Override
    public String getName() {
        return "EurexPriceService";
    }
}
