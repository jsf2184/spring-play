package com.jeff.fischman.price.service.config.eurex;

import com.jeff.fischman.price.service.EurexPriceService;
import com.jeff.fischman.price.service.PlatformDependency;
import com.jeff.fischman.price.service.PriceService;
import com.jeff.fischman.price.service.PriceServiceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Bean;

//@Configuration
public class EurexConfig {
    public static Logger _log = LoggerFactory.getLogger(PriceServiceFactory.class) ;
    ConfigurableListableBeanFactory _beanFactory;

    public EurexConfig(ConfigurableListableBeanFactory beanFactory) {
        _beanFactory = beanFactory;
        _log.info(String.format("EurexConfig constructor invoked, beanFactory %s null",
                                beanFactory == null ? "is" : "is not"));
    }

    @Bean
    PriceService eurexPriceService() {
        EurexPriceService res = _beanFactory.createBean(EurexPriceService.class);
        return res;
    }

    @Bean
    PlatformDependency eurexDependency() {
        PlatformDependency res = _beanFactory.createBean(PlatformDependency.Eurex.class);
        return res;
    }

    public ConfigurableListableBeanFactory getBeanFactory() {
        return _beanFactory;
    }
}
