package com.omernaci.camelexample.route;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.redis.processor.idempotent.RedisIdempotentRepository;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class DirectDebitPaymentRoute extends RouteBuilder {

    private final RedisTemplate<String, String> redisTemplate;

    public DirectDebitPaymentRoute(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void configure() {
        RedisIdempotentRepository redisIdRepository = new RedisIdempotentRepository(redisTemplate, "processed-direct-debits");

        from("direct:processDirectDebits")
            .idempotentConsumer(header("paymentId"), redisIdRepository)
                .skipDuplicate(false) // Skip processing of duplicate direct debit payments
                .log("Processing direct debit payment with ID: ${header.paymentId}")
                .process(exchange -> {
                    String paymentId = exchange.getIn().getHeader("paymentId", String.class);
                    boolean isDuplicate = exchange.getProperty(Exchange.DUPLICATE_MESSAGE, boolean.class);
                    if (isDuplicate) {
                        System.out.println("Duplicate paymentId: " + paymentId);
                        exchange.getIn().setBody("Duplicate direct debit payment. This payment is already processed.");
                    } else {
                        System.out.println("Processing direct debit payment with ID: " + paymentId);
                        exchange.getIn().setBody("Payment processed successfully.");
                    }
                })
            .end();
    }
}
