package com.jeff.fischman;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class ContextHolder {
    public static Logger _log = LoggerFactory.getLogger(ContextHolder.class) ;

    static ApplicationContext _applicationContext;

    public ContextHolder(ApplicationContext applicationContext) {
        _log.info(String.format("ContextHolder constructor invoked w/applicationContext arg of type: %s", applicationContext.getClass().getSimpleName()));
        _applicationContext = applicationContext;
    }
}
