package com.evelyn.myapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@Configuration
@SpringBootApplication()
public class MySocialApplication {

	public static void main(String[] args) {
		SpringApplication.run(MySocialApplication.class, args);
	}

}
