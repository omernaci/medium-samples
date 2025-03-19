package com.omernaci.pricing;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/pricing")
public class PricingController {
    private static final Map<String, Double> PRICING_DB = Map.of(
            "1001", 199.99,
            "2002", 99.99,
            "3003", 49.99
    );

    @GetMapping("/{productId}")
    public ResponseEntity<Double> getPrice(@PathVariable String productId) {
        return ResponseEntity.ok(PRICING_DB.getOrDefault(productId, -1.0));
    }

}
