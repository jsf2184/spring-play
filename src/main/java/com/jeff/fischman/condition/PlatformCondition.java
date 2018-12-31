package com.jeff.fischman.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class PlatformCondition implements Condition {
    protected String _platformName;

    public PlatformCondition(String platformName) {
        _platformName = platformName;
    }

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        String priceService = context.getEnvironment().getProperty("price.platform");
        return  _platformName.equals(priceService);
    }

    public static class Cme extends  PlatformCondition {
        public Cme() {
            super("cme");
        }
    }
    public static class Eurex extends  PlatformCondition {
        public Eurex() {
            super("eurex");
        }
    }
}
