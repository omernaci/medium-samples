package com.omernaci.asyncexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class AsyncExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(AsyncExampleApplication.class, args);
	}

}
