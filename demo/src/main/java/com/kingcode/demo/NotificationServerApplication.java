package com.kingcode.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
@EnableRetry
public class NotificationServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotificationServerApplication.class, args);
	}

}
