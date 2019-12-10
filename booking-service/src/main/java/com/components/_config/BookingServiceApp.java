package com.components._config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("com.components.repositories")
@SpringBootApplication(scanBasePackages = "com.components.*")
@EntityScan(basePackages = "com.components.*")
public class BookingServiceApp {
    public static void main( String[] args ) {
        SpringApplication.run(BookingServiceApp.class, args);
    }
}