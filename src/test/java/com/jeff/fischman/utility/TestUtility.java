package com.jeff.fischman.utility;

import com.jeff.fischman.EnvironmentPropertyAdder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestUtility {
    public static AnnotationConfigApplicationContext wireContext(Class<?> configClass,
                                                                 String propertyName,
                                                                 Object propertyValue)
    {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        EnvironmentPropertyAdder propertyAdder = new EnvironmentPropertyAdder(context.getEnvironment());
        propertyAdder
                .enqueueProperty(propertyName, propertyValue)
                .submit("TestProperties");
        if (configClass != null) {
            context.register(configClass);
        }
        context.scan("com.jeff.fischman.context");
        context.refresh();
        return context;
    }

}
