package com.example.user_microservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableCaching
@EnableDiscoveryClient
public class UserMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserMicroserviceApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadData(UserRepository userRepository) {
		return args -> {
			userRepository.save(new User(null, "Onur Koca", "onurkoca@example.com"));
			userRepository.save(new User(null, "Jane Smith", "jane@example.com"));
			userRepository.save(new User(null, "Mike Johnson", "mike@example.com"));
		};
	}
}
