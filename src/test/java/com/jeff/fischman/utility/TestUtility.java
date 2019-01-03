package com.jeff.fischman.utility;

import com.jeff.fischman.EnvironmentPropertyAdder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestUtility {
    public static AnnotationConfigApplicationContext wireContext(Class<?> configClass,
                                                                 String propertyName,
                                                                 Object propertyValue,
                                                                 String packageToScan)
    {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        EnvironmentPropertyAdder propertyAdder = new EnvironmentPropertyAdder(context.getEnvironment());
        propertyAdder
                .enqueueProperty(propertyName, propertyValue)
                .submit("TestProperties");
        if (configClass != null) {
            context.register(configClass);
        }

        if (packageToScan != null) {
            context.scan(packageToScan);

        }
//        context.scan("com.jeff.fischman.context");
        context.refresh();
        return context;
    }


    public static AnnotationConfigApplicationContext wireContext(String packageToScan)
    {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(packageToScan);
        return context;
    }

}
