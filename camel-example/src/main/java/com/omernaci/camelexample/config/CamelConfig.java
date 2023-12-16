package com.omernaci.camelexample.config;

import org.apache.camel.CamelContext;
import org.apache.camel.spring.boot.CamelContextConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CamelConfig {

    @Bean
    public CamelContextConfiguration contextConfiguration() {
        return new CamelContextConfiguration() {
            @Override
            public void beforeApplicationStart(CamelContext context) {
                // Enable JMX in Camel
                context.getManagementStrategy().getManagementAgent().setEndpointRuntimeStatisticsEnabled(true);
            }

            @Override
            public void afterApplicationStart(CamelContext context) {
                // Additional configuration after Camel context starts
            }
        };
    }

}
