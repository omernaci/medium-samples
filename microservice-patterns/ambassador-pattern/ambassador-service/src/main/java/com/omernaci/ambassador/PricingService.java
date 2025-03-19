package com.omernaci.ambassador;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class PricingService {

    private final RestTemplate restTemplate;

    @Autowired
    public PricingService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Double getPrice(String productId) {
        String url = "http://pricing-service/pricing/" + productId;
        return restTemplate.getForObject(url, Double.class);
    }
}
