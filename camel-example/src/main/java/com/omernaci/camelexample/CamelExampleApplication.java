package com.omernaci.camelexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class CamelExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(CamelExampleApplication.class, args);
	}

}
