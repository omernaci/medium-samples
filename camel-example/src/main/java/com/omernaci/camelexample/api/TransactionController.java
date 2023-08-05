package com.omernaci.camelexample.api;

import com.omernaci.camelexample.persistence.entity.Transaction;
import org.apache.camel.ProducerTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
