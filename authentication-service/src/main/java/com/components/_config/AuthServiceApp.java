package com.components._config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.components.*")
@EntityScan(basePackages = "com.components.*")
@EnableJpaRepositories("com.components.repositories")
public class AuthServiceApp {
    public static void main( String[] args ) {
        SpringApplication.run(AuthServiceApp.class, args);
    }
}