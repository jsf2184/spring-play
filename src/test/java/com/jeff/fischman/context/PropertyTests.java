package com.jeff.fischman.context;

import com.jeff.fischman.play.environment.EnvironmentUser;
import com.jeff.fischman.play.environment.PropertyUser;
import com.jeff.fischman.utility.TestUtility;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.SimpleCommandLinePropertySource;


public class PropertyTests {

    public static Logger _log = LoggerFactory.getLogger(PropertyTests.class) ;
    private final static String envPackage = "com.jeff.fischman.play.environment";

    @Test
    public void testAddPropertyToContext() {
        AnnotationConfigApplicationContext context = TestUtility.wireContext(null, "num.property", 5, null);
        Assert.assertEquals(new Integer(5), context.getEnvironment().getProperty("num.property", Integer.class));
    }

    @Test
    public void testEnvironmentWithPropertyInjectedIntoCreatedComponent() {
        AnnotationConfigApplicationContext context = TestUtility.wireContext(null,
                                                                             "num.property",
                                                                             5,
                                                                             envPackage);

        // note the default name of the inner class bean we created.
        EnvironmentUser environmentUser = (EnvironmentUser) context.getBean("environmentUser");
        Assert.assertSame(context.getEnvironment(), environmentUser.getEnvironment());
        Assert.assertEquals(new Integer(5), environmentUser.getEnvironment().getProperty("num.property", Integer.class));
    }

    @Test
    public void testPropertyInjectedIntoCreatedComponentWithValueNotation() {
        AnnotationConfigApplicationContext context = TestUtility.wireContext(null,
                                                                             "num.property",
                                                                             5,
                                                                             envPackage);

        PropertyUser propertyUser = (PropertyUser) context.getBean("propertyUser");
        Assert.assertEquals(5, propertyUser.getNum());
    }

    @Test
    public void testPropertyExtractionFromSimpleCommandLinePropertySource() {
        String[] args = {"--price.platform=cme"};
        SimpleCommandLinePropertySource propertySource = new SimpleCommandLinePropertySource(args);
        String property = propertySource.getProperty("price.platform");
        Assert.assertEquals("cme", property);
    }



}
