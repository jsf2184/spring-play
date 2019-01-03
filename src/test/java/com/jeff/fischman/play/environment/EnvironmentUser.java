package com.jeff.fischman.play.environment;

import com.jeff.fischman.context.PropertyTests;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class EnvironmentUser {
    private Environment _environment;

    public EnvironmentUser(Environment environment) {
        _environment = environment;
        PropertyTests._log.info("EnvironmentUser constructor");
    }

    public Environment getEnvironment() {
        return _environment;
    }
}
