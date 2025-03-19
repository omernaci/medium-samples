package com.omernaci.ambassador;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ambassador")
public class AmbassadorController {

    private final PricingService pricingService;

    public AmbassadorController(PricingService pricingService) {
        this.pricingService = pricingService;
    }

    @GetMapping("/price/{productId}")
    public ResponseEntity<Double> getPrice(@PathVariable String productId) {
        Double price = pricingService.getPrice(productId);
        return ResponseEntity.ok(price);
    }
}