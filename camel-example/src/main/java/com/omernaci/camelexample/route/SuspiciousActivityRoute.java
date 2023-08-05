package com.omernaci.camelexample.route;

import com.omernaci.camelexample.persistence.entity.Transaction;
import com.omernaci.camelexample.service.TransactionService;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class SuspiciousActivityRoute extends RouteBuilder {

    private final TransactionService transactionService;

    public SuspiciousActivityRoute(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @Override
    public void configure() {
        from("direct:suspiciousTransactions")
            .process(exchange -> {
                Transaction transaction = exchange.getIn().getBody(Transaction.class);
                transactionService.logSuspiciousActivity(transaction);
            });
    }

}
