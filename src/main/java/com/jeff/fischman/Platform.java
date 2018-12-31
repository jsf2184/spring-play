package com.jeff.fischman;

import com.jeff.fischman.price.service.config.cme.CmeConfig;
import com.jeff.fischman.price.service.config.eurex.EurexConfig;

public enum Platform {
    eurex(EurexConfig.class),
    cme(CmeConfig.class);

    Class<?> _configClass;

    Platform(Class<?> configClass) {
        _configClass = configClass;
    }

    public Class<?> getConfigClass() {
        return _configClass;
    }
}
