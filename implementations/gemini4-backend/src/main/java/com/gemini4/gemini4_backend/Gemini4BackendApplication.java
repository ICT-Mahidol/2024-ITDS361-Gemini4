package com.gemini4.gemini4_backend;

import com.gemini4.gemini4_backend.controller.OCSController;
import com.gemini4.gemini4_backend.model.Telescope;
import com.gemini4.gemini4_backend.repository.TelescopeRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class Gemini4BackendApplication {
	@Autowired
	private TelescopeRepository telescopeRepository;

	@PostConstruct
	public void initDefaultTelescope() {
		if (telescopeRepository.count() == 0) {
			Telescope defaultTelescope = new Telescope();
			telescopeRepository.save(defaultTelescope);
			System.out.println("Default telescope added at startup.");
		} else {
			System.out.println("Telescope(s) already exist, no default added.");
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(Gemini4BackendApplication.class, args);
	}

}
