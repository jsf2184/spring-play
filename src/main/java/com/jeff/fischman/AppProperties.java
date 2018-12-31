package com.jeff.fischman;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class AppProperties {
    public static Logger _log = LoggerFactory.getLogger(AppProperties.class) ;

    AbstractEnvironment _environment;

    public AppProperties(Environment environment) {
        _environment = (AbstractEnvironment) environment;
        report();
    }

    public void report() {
        Map<String, Object> map = mapSources();
    }

    public Map<String, Object> mapSources() {
        HashMap<String, Object> res = new HashMap<>();
        for (PropertySource ps : _environment.getPropertySources()) {
            if (ps instanceof MapPropertySource) {
                String name = ps.getName();
                _log.info(String.format("AppProperties.mapSources(): processing source: %s", name));
                Map<String, Object> map = ((MapPropertySource) ps).getSource();
                map.keySet().forEach(k -> _log.info(String.format("    %s -> %s", k, map.get(k))));
                res.putAll(map);
            }
        }
        return res;
    }

    public String getProperty(String propertyName) {
        String res = _environment.getProperty(propertyName);
        return res;
    }
}
