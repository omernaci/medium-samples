
### The [Message Filter](https://camel.apache.org/components/3.21.x/eips/filter-eip.html) from the EIP patterns allows you to filter messages.

```java
@RestController
public class TransactionController {

    private final ProducerTemplate producerTemplate;

    public TransactionController(ProducerTemplate producerTemplate) {
        this.producerTemplate = producerTemplate;
    }

    @PostMapping("/incomingTransactions")
    public ResponseEntity<String> handleIncomingTransactions(@RequestBody Transaction transaction) {
        producerTemplate.sendBody("direct:incomingTransactions", transaction);
        return ResponseEntity.status(HttpStatus.OK).body("Transaction received for processing!");
    }

}

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
```

```logcatfilter
o.a.c.impl.engine.AbstractCamelContext   : Apache Camel 4.0.0-RC2 (camel-1) is starting
o.a.c.impl.engine.AbstractCamelContext   : Routes startup (started:2)
o.a.c.impl.engine.AbstractCamelContext   :     Started route1 (direct://suspiciousTransactions)
o.a.c.impl.engine.AbstractCamelContext   :     Started route2 (direct://incomingTransactions)

these log lines show that the Camel context has started successfully, and it is managing two routes (route1 and route2) with their corresponding endpoints
```


- `producerTemplate.sendBody("direct:incomingTransactions", transaction)`: The producerTemplate is an instance of Camel's ProducerTemplate, which allows us to send messages (transactions) to Camel routes for further processing. In this line, we send the received Transaction object to the Camel route with the endpoint `direct:incomingTransactions`.

- The route starts with the `from` method, which sets the starting endpoint of the route. In this case, the route starts from the `direct:incomingTransactions` endpoint, meaning it expects incoming messages (transactions) from a direct component.

- The route uses a `choice` statement, which is a conditional construct in Apache Camel. It allows you to define different routes based on conditions.

- The `when` statement specifies a condition that checks if the sourceLocation property of the incoming message (transaction) is equal to 'HighRiskCountry'.

- If the condition evaluates to true (i.e., the sourceLocation is 'HighRiskCountry'), the route goes to the `direct:suspiciousTransactions` endpoint.

- If the condition in the `when` statement is false (i.e., the sourceLocation is not 'HighRiskCountry'), the route goes to the otherwise statement.

- In the `otherwise` statement, the route continues to the `to` method, which sends the message (transaction) to the jpa component. The jpa component is used to persist the message (transaction) into the database using the Java Persistence API (JPA).

Sample CURL Requests:
```
curl -X POST -H "Content-Type: application/json" -d '{
  "accountNumber": "9876543210",
  "amount": 500.00,
  "sourceLocation": "HighRiskCountry",
  "paymentMethod": "CREDIT_CARD",
  "transactionDate": "2023-08-03T10:15:30"
}' http://localhost:8080/transactions/income

curl -X POST -H "Content-Type: application/json" -d '{
  "accountNumber": "1234567890",
  "amount": 100.00,
  "sourceLocation": "USA",
  "paymentMethod": "CREDIT_CARD",
  "transactionDate": "2023-08-03T10:15:30"
}' http://localhost:8080/transations/income

```


----

### The [Idempotent Consumer](https://camel.apache.org/components/3.21.x/eips/idempotentConsumer-eip.html) from the EIP patterns is used to filter out duplicate messages.

To start Redis Stack server using the redis-stack-server image, run the following command in your terminal:
`docker run -d --name redis-stack-server -p 6379:6379 redis/redis-stack-server:latest`

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-redis</artifactId>
</dependency>
<dependency>
    <groupId>org.apache.camel</groupId>
    <artifactId>camel-spring-redis</artifactId>
    <version>4.0.0-RC2</version>
</dependency>
```

```java
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
                .skipDuplicate(false) // .skipDuplicate(true) : Skip processing of duplicate direct debit payments
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
```

- The Idempotent Consumer filters out duplicate messages based on the message ID (paymentId in this case) and the idempotent repository (redisIdRepository).

  - header("paymentId"): The Idempotent Consumer will use this value as the message ID for duplicate checks.
  - redisIdRepository: This is the RedisIdempotentRepository instance that holds the set of processed direct debit payment IDs. It will be used to check whether a message with the same "paymentId" has been processed before.

- `skipDuplicate(false)` : Idempotent Consumer will not skip processing duplicate direct debit payments. 
   Instead, it will process each message, whether it is a duplicate or not. If you want to switch to duplicate records directly, you must set this property true.

- `Exchange.DUPLICATE_MESSAGE` property from the Camel Exchange. The property is set by the Idempotent Consumer to indicate whether the message is a duplicate or not.