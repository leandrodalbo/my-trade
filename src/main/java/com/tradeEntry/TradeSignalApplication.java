package com.tradeEntry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class TradeSignalApplication {

    public static void main(String[] args) {
        SpringApplication.run(TradeSignalApplication.class, args);
    }

}
