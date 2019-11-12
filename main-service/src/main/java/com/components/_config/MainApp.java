package com.components._config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication(scanBasePackages = "com.components.*")
@EnableCaching
public class MainApp {
    public static void main( String[] args ) {
        SpringApplication.run(MainApp.class, args);
    }
}
