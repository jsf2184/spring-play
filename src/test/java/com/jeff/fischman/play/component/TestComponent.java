package com.jeff.fischman.play.component;

import org.springframework.stereotype.Component;

@Component
public class TestComponent {

    @Component
    public static class C1 extends TestComponent {

    }

    @Component
    public static class C2 extends TestComponent {
    }

}
