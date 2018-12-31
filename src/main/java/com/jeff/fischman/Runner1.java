package com.jeff.fischman;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

@Service
public class Runner1 implements CommandLineRunner {

    public static Logger _log = LoggerFactory.getLogger(Runner1.class) ;

    public Runner1() {
        _log.info("Runner1 constructed");
    }

    @Override
    public void run(String... args) throws Exception {
        _log.info("Runner1.run() invoked");
    }
}
