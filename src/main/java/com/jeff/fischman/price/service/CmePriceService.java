package com.jeff.fischman.price.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CmePriceService extends PriceService {
    public static Logger _log = LoggerFactory.getLogger(EurexPriceService.class) ;

    public CmePriceService(PlatformDependency dependency) {
        super("Cme", dependency);
    }

    @Override
    public String getName() {
        return "CmePriceService";
    }
}
