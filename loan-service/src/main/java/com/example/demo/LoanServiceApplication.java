package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient
@CrossOrigin("*")
@RestController
public class LoanServiceApplication {
	private final Environment environment;
	
	public LoanServiceApplication(Environment environment) {
		super();
		this.environment = environment;
	}

	public static void main(String[] args) {
		SpringApplication.run(LoanServiceApplication.class, args);
	}
	
	@GetMapping("/status")
	public ResponseEntity<?> checkStatus(){
		return ResponseEntity.ok("app running on port "+environment.getProperty("local.server.port"));
	}

}
