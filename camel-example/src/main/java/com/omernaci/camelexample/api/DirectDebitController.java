package com.omernaci.camelexample.api;

import com.omernaci.camelexample.persistence.entity.DirectDebitPayment;
import org.apache.camel.ProducerTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/direct-debit")
public class DirectDebitController {

    private final ProducerTemplate producerTemplate;

    public DirectDebitController(ProducerTemplate producerTemplate) {
        this.producerTemplate = producerTemplate;
    }

    @PostMapping("/payment")
    public ResponseEntity<String> processDirectDebitPayment(@RequestBody DirectDebitPayment directDebitPayment) {
        String response = producerTemplate.requestBodyAndHeader("direct:processDirectDebits", null, "paymentId", directDebitPayment.getPaymentId(), String.class);        //producerTemplate.sendBodyAndHeader("direct:processDirectDebits", directDebitPayment, "paymentId", directDebitPayment.getPaymentId());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}