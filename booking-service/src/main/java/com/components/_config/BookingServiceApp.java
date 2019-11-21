package com.components._config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.components.*")
public class BookingServiceApp {
    public static void main( String[] args ) {
        SpringApplication.run(BookingServiceApp.class, args);
    }
}