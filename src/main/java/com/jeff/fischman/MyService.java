package com.jeff.fischman;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.stereotype.Component;

@Component
public class MyService {
    public static Logger _log = LoggerFactory.getLogger(MyService.class) ;

    PrototypeComponent _prototypeComponent;
    BeanFactory _beanFactory;

//    @Autowired
    public MyService(PrototypeComponent prototypeComponent,
                     BeanFactory beanFactory)
    {
        _prototypeComponent = prototypeComponent;
        _beanFactory = beanFactory;
        _log.info(String.format("MyService constructor, w/beanFactory: %s",
                                beanFactory.getClass().getSimpleName()));
        
    }

}
