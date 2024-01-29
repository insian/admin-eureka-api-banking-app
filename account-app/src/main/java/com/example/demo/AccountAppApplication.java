package com.example.demo;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import com.example.demo.model.Account;
import com.example.demo.repo.AccountRepository;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@SpringBootApplication
@EnableDiscoveryClient
public class AccountAppApplication {
	private final AccountRepository accountRepository;
	public static void main(String[] args) {
		SpringApplication.run(AccountAppApplication.class, args);
	}

	@PostConstruct
	public void init() {
		/*
		List<Account> result = Stream.of(
		new Account(UUID.randomUUID().toString(),"Sachin","Mumbai","sachin@email.com",5000.00,1000.00),
		new Account(UUID.randomUUID().toString(),"Soham","Kolkata","soham@email.com",2000.00,0.00),
		new Account(UUID.randomUUID().toString(),"Subho","Kalyani","subho@email.com",2300.00,500.00),
		new Account(UUID.randomUUID().toString(),"Shayam","Bhubhaneshwar","shayam@email.com",2500.00,1000.0))
				.collect(Collectors.toList());
		for(Account a: result) {
			accountRepository.createAccount(a);
		}*/
	}

}