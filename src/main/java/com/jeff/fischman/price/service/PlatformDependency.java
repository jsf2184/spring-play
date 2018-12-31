package com.jeff.fischman.price.service;

import com.jeff.fischman.condition.PlatformCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PlatformDependency {
    public static Logger _log = LoggerFactory.getLogger(PlatformDependency.class) ;
    protected String _platformName;

    protected PlatformDependency(String platformName) {
        _platformName = platformName;
        _log.info(String.format("%sDependency constructor", platformName));
    }

    String getName() {
        return _platformName;
    }
    
    public static class Cme extends PlatformDependency {
        public Cme() {
            super("Cme");
        }
    }

    public static class Eurex extends PlatformDependency {
        public Eurex() {
            super("Eurex");
        }
    }

}
