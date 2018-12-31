package com.jeff.fischman.price.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class PriceService {
    public static Logger _log = LoggerFactory.getLogger(PriceService.class) ;
    PlatformDependency _dependency;
    private String _name;

    public PriceService(String name, PlatformDependency dependency) {
        _name = name;
        _dependency = dependency;
        _log.info(String.format("%sPriceService constructor w/ %s dependency", _name, getDependencyName()));
    }

    public String getName() {
        return _name;
    }

    public String getDependencyName() {
        return _dependency.getName();
    }

}
