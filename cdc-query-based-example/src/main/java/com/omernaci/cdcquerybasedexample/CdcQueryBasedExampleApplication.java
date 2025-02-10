package com.omernaci.cdcquerybasedexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CdcQueryBasedExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(CdcQueryBasedExampleApplication.class, args);
    }

}
