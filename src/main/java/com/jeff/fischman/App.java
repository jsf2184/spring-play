package com.jeff.fischman;


import com.jeff.fischman.price.service.PlatformDependency;
import com.jeff.fischman.price.service.PriceService;
import com.jeff.fischman.price.service.PriceServiceFactory;
import com.jeff.fischman.price.service.config.ConditionalConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Scanner;
import java.util.stream.IntStream;

/**
 * Hello world!
 *
 */

@SpringBootApplication
public class App 
{
    public static Logger _log = LoggerFactory.getLogger(App.class) ;

    private ConfigurableApplicationContext _context;
    private String _pricePlatform;


    public static void main(String[] args )
    {

        PriceService priceService = PriceServiceFactory.create(args);

        Class<?> configClasses[] = {App.class, ConditionalConfig.class };
        ConfigurableApplicationContext context = SpringApplication.run(configClasses, args);
        _log.info(String.format("main(): SpringApplication.run() returned %s", context.getClass().getSimpleName()));

        PlatformDependency dependency =  (PlatformDependency) context.getBean("dependency");
        System.out.printf( "getBean(\"dependency\") returned value: %s\n", dependency != null );

        IntStream.range(0,4).forEach(i -> {
            _log.info(String.format("getBean() w/i= %d\n", i));
            PrototypeComponent prototypeComponent = context.getBean(PrototypeComponent.class);
            System.out.printf( "getBean(PrototypeComponent.class) returns null == %s\n", prototypeComponent == null );

        });

        App app = context.getBean(App.class);
//        App application = new App(context);
        app.run();

    }

    public App(ConfigurableApplicationContext context,
               @Value("${price.platform}") String pricePlatform)
    {
        _context = context;
        _pricePlatform = pricePlatform;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);

        PriceService priceService = (PriceService)_context.getBean("priceService");
        _log.info(String.format("priceService: %s uses dependency: %s",
                                priceService.getName(),
                                priceService.getDependencyName()));
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.startsWith("q")) {
                break;
            }
            _log.info("App.run(): still running");
        }
        _log.info("App.run(): done running");
        _context.close();
    }



}
