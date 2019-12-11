package com.components._config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;

import javax.persistence.Entity;

@SpringBootApplication(scanBasePackages = "com.components.*")
@EnableCaching
@EntityScan(basePackages = "com.components.*")
public class MainApp {
    public static void main( String[] args ) {
        SpringApplication.run(MainApp.class, args);
    }
}
