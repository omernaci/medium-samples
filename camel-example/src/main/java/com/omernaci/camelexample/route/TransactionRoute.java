package com.omernaci.camelexample.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class TransactionRoute extends RouteBuilder {

    @Override
    public void configure() {
        from("direct:incomingTransactions")
            .choice()
                .when(simple("${body.sourceLocation} == 'HighRiskCountry'"))
                .to("direct:suspiciousTransactions")
            .otherwise()
                .to("jpa:com.omernaci.camelexample.persistence.entity.Transaction");
    }

}
