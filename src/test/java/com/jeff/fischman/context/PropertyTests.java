package com.jeff.fischman.context;

import com.jeff.fischman.utility.TestUtility;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;


public class PropertyTests {

    public static Logger _log = LoggerFactory.getLogger(PropertyTests.class) ;

    @Component
    public static class EnvironmentUser {
        Environment _environment;

        public EnvironmentUser(Environment environment) {
            _environment = environment;
            _log.info("EnvironmentUser constructor");
        }

        public Environment getEnvironment() {
            return _environment;
        }
    }
    @Component
    public static class PropertyUser {
        int _num;

        public PropertyUser(@Value("${num.property}") int num) {
            _num = num;
            _log.info("PropertyUser constructor");
        }

        public int getNum() {
            return _num;
        }
    }


    @Test
    public void testAddPropertyToContext() {
        AnnotationConfigApplicationContext context = TestUtility.wireContext(null, "num.property", 5);
        Assert.assertEquals(new Integer(5), context.getEnvironment().getProperty("num.property", Integer.class));
    }

    @Test
    public void testEnvironmentWithPropertyInjectedIntoCreatedComponent() {
        AnnotationConfigApplicationContext context = TestUtility.wireContext(null, "num.property", 5);
        // note the default name of the inner class bean we created.
        EnvironmentUser environmentUser = (EnvironmentUser) context.getBean("propertyTests.EnvironmentUser");
        Assert.assertSame(context.getEnvironment(), environmentUser.getEnvironment());
        Assert.assertEquals(new Integer(5), environmentUser.getEnvironment().getProperty("num.property", Integer.class));
    }

    @Test
    public void testPropertyInjectedIntoCreatedComponentWithValueNotation() {
        AnnotationConfigApplicationContext context = TestUtility.wireContext(null, "num.property", 5);
        PropertyUser propertyUser = (PropertyUser) context.getBean("propertyTests.PropertyUser");
        Assert.assertEquals(5, propertyUser.getNum());
    }



}
