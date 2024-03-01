package com.tech.lab4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.tech.lab4.dao")
@EntityScan(basePackages = "com.tech.lab4.entities")
public class Lab4Application {
	public static void main(String[] args) {
		SpringApplication.run(Lab4Application.class, args);
	}
}