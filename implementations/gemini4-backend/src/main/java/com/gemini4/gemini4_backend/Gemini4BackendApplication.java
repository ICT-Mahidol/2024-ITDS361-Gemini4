package com.gemini4.gemini4_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
<<<<<<< Updated upstream

@SpringBootApplication
=======
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
>>>>>>> Stashed changes
public class Gemini4BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(Gemini4BackendApplication.class, args);
	}

}
