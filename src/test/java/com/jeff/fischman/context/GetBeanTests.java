package com.jeff.fischman.context;

import com.jeff.fischman.play.component.TestComponent;
import com.jeff.fischman.utility.TestUtility;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class GetBeanTests {


    @Test
    public void testGetBeanWithDerivedClassTypeC1Works() {
        AnnotationConfigApplicationContext context = TestUtility.wireContext("com.jeff.fischman.play.component");
        TestComponent.C1 bean = context.getBean(TestComponent.C1.class);
        Assert.assertNotNull(bean);
    }
    @Test
    public void testGetBeanWithDerivedClassTypeC2Works() {
        AnnotationConfigApplicationContext context = TestUtility.wireContext("com.jeff.fischman.play.component");
        TestComponent.C2 bean = context.getBean(TestComponent.C2.class);
        Assert.assertNotNull(bean);
    }

    @Test
    public void testGetBeanWithBaseClassTypeIsAmbiguous() {
        AnnotationConfigApplicationContext context = TestUtility.wireContext("com.jeff.fischman.play.component");
        boolean caught = false;
        try {
            TestComponent bean = context.getBean(TestComponent.class);
        } catch (NoUniqueBeanDefinitionException ignore) {
            caught = true;
        }
        Assert.assertTrue(caught);
    }

    @Test
    public void testGetBeanWithBaseClassWorksWithDefaultName() {
        AnnotationConfigApplicationContext context = TestUtility.wireContext("com.jeff.fischman.play.component");
        TestComponent bean = (TestComponent) context.getBean("testComponent");
        Assert.assertNotNull(bean);
    }

    @Test
    public void testGetBeanWithBaseClassAmbiguousWithDefaultNameAndBaseClassType() {
        // Note this suprises me because the combo of class and name should remove ambiguity but it does not
        AnnotationConfigApplicationContext context = TestUtility.wireContext("com.jeff.fischman.play.component");
        boolean caught = false;
        try {
            TestComponent bean = context.getBean(TestComponent.class, "testComponent");
        } catch (NoUniqueBeanDefinitionException ignore) {
            caught = true;
        }
        Assert.assertTrue(caught);
    }

    @Test
    public void testGetBeanWithDerivedClassC1WorksWithDefaultName() {
        AnnotationConfigApplicationContext context = TestUtility.wireContext("com.jeff.fischman.play.component");
        TestComponent.C1 bean = (TestComponent.C1) context.getBean("testComponent.C1");
        Assert.assertNotNull(bean);
    }

    @Test
    public void testGetBeanWithDerivedClassC2WorksWithDefaultName() {
        AnnotationConfigApplicationContext context = TestUtility.wireContext("com.jeff.fischman.play.component");
        TestComponent.C2 bean = (TestComponent.C2) context.getBean("testComponent.C2");
        Assert.assertNotNull(bean);
    }

}
