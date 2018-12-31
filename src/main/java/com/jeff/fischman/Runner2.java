package com.jeff.fischman;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

@Service
public class Runner2 implements CommandLineRunner {

    public static Logger _log = LoggerFactory.getLogger(Runner2.class) ;

    public Runner2() {
        _log.info("Runner2 constructed");
    }

    @Override
    public void run(String... args) throws Exception {
        _log.info("Runner2.run() invoked");
    }
}
