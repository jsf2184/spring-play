package com.jeff.fischman.play.environment;

import com.jeff.fischman.context.PropertyTests;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PropertyUser {
    private int _num;

    public PropertyUser(@Value("${num.property}") int num) {
        _num = num;
        PropertyTests._log.info("PropertyUser constructor");
    }

    public int getNum() {
        return _num;
    }
}
