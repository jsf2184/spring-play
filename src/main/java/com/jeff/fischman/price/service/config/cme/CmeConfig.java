package com.jeff.fischman.price.service.config.cme;

import com.jeff.fischman.price.service.CmePriceService;
import com.jeff.fischman.price.service.PlatformDependency;
import com.jeff.fischman.price.service.PriceService;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.Bean;

//@Configuration
public class CmeConfig {
    DefaultListableBeanFactory _beanFactory;

    public CmeConfig(DefaultListableBeanFactory beanFactory) {
        _beanFactory = beanFactory;
    }

    @Bean
    PriceService cmePriceService() {
        CmePriceService res = _beanFactory.createBean(CmePriceService.class);
        return res;
    }

    @Bean(name="platformDependency")
    PlatformDependency cmeDependency() {
        PlatformDependency res = _beanFactory.createBean(PlatformDependency.Cme.class);
        return res;
    }
}
