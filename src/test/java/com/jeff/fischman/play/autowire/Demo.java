package com.jeff.fischman.play.autowire;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// There Derivations of this Demo Class demonstrate our ability to disambiguate which constructor
// will be used (when there is more than one) with the use of @Autowire to select a constructor.
//
@SuppressWarnings("WeakerAccess")
public class Demo {
    public static Logger _log = LoggerFactory.getLogger(Demo.class) ;

    @Component("dep")
    public static class Dep {
        public Dep() {
           _log.info("OwnerWithTwoConstructors.Dep constructor");
        }
    }
    private Dep _dep;

    public Demo(Dep dep) {
        _log.info("OwnerWithTwoConstructors constructor that takes a Dependency");
        _dep = dep;
    }

    public Dep getDep() {
        return _dep;
    }

    // Note the only difference between the two Demo derived classes that follow is
    // which constructor is @Autowired

    @Component
    public static class AutowireDefConstructor extends Demo {
        public AutowireDefConstructor(Dep dep) {
            super(dep);
        }
        @Autowired
        public AutowireDefConstructor() {
            super(null);
        }
    }

    @Component
    public static class AutowireDepConstructor extends Demo {
        @Autowired
        public AutowireDepConstructor(Dep dep) {
            super(dep);
        }
        public AutowireDepConstructor() {
            super(null);
        }
    }


}
