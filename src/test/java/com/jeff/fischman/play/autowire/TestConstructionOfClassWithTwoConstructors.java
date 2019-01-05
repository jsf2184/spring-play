package com.jeff.fischman.play.autowire;

import com.jeff.fischman.utility.TestUtility;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestConstructionOfClassWithTwoConstructors {
    @Test
    public void testAbilityToSelectConstructorThatTakesDep() {
        AnnotationConfigApplicationContext context = TestUtility.wireContext("com.jeff.fischman.play.autowire");
        Demo bean = context.getBean(Demo.AutowireDepConstructor.class);
        Assert.assertNotNull(bean);
        Assert.assertNotNull(bean.getDep());
    }
    @Test
    public void testAbilityToSelectConstructorThatTakesNoArg() {
        AnnotationConfigApplicationContext context = TestUtility.wireContext("com.jeff.fischman.play.autowire");
        Demo bean = context.getBean(Demo.AutowireDefConstructor.class);
        Assert.assertNotNull(bean);
        Assert.assertNull(bean.getDep());
    }

}
