package com.jeff.fischman;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Component
@Scope(value="prototype")
public class PrototypeComponent {
    public static Logger _log = LoggerFactory.getLogger(PrototypeComponent.class) ;

    public PrototypeComponent() {
        _log.info("In PrototypeComponent constructor");
    }
}
