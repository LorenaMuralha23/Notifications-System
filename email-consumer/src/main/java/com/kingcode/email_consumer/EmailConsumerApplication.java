package com.kingcode.email_consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
public class EmailConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmailConsumerApplication.class, args);
	}

}
