package com.cirkuits.cirkuitsapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class CirkuitsApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CirkuitsApiApplication.class, args);
	}

}
