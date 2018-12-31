package com.jeff.fischman.price.service.config;

import com.jeff.fischman.condition.PlatformCondition;
import com.jeff.fischman.price.service.CmePriceService;
import com.jeff.fischman.price.service.EurexPriceService;
import com.jeff.fischman.price.service.PlatformDependency;
import com.jeff.fischman.price.service.PriceService;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;

public class ConditionalConfig {
    ConfigurableListableBeanFactory _beanFactory;

    public ConditionalConfig(ConfigurableListableBeanFactory beanFactory) {
        _beanFactory = beanFactory;
    }

    @Bean(name="priceService")
    @Conditional(PlatformCondition.Eurex.class)
    PriceService eurexPriceService() {
        PriceService priceService = _beanFactory.createBean(EurexPriceService.class);
        return priceService;
    }

    @Bean(name="priceService")
    @Conditional(PlatformCondition.Cme.class)
    PriceService cmePriceService() {
        PriceService priceService = _beanFactory.createBean(CmePriceService.class);
        return priceService;
    }

    @Bean(name="dependency")
    @Conditional(PlatformCondition.Cme.class)
    PlatformDependency cmeDependency() {
        PlatformDependency res = _beanFactory.createBean(PlatformDependency.Cme.class);
        return res;
    }

    @Bean(name="dependency")
    @Conditional(PlatformCondition.Eurex.class)
    PlatformDependency eurexDependency() {
        PlatformDependency res = _beanFactory.createBean(PlatformDependency.Eurex.class);
        return res;
    }

}
