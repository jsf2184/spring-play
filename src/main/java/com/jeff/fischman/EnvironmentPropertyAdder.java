package com.jeff.fischman;

import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;

import java.util.HashMap;
import java.util.Map;

public class EnvironmentPropertyAdder {
    ConfigurableEnvironment _environment;
    Map<String, Object> _queuedProperties;

    public EnvironmentPropertyAdder(ConfigurableEnvironment environment) {
        _environment = environment;
        _queuedProperties = new HashMap<>();
    }

    public EnvironmentPropertyAdder enqueueProperty(String key, Object value) {
        _queuedProperties.put(key, value);
        return this;
    }

    public void submit(String sourceName) {
        if (_queuedProperties.isEmpty()) {
            return;
        }
        MutablePropertySources propertySources = _environment.getPropertySources();
        MapPropertySource propertySource = new MapPropertySource(sourceName, _queuedProperties);
        propertySources.addFirst(propertySource);
        _queuedProperties = new HashMap<>();
    }

}
