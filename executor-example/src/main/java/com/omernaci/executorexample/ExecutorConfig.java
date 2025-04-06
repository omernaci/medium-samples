package com.omernaci.executorexample;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class ExecutorConfig {

    @Bean(name = "notificationExecutorService")
    public ExecutorService notificationExecutorService() {
        return Executors.newFixedThreadPool(10);
    }

}
