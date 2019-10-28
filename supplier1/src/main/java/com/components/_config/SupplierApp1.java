package com.components._config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.components.*")
public class SupplierApp1 {
    public static void main( String[] args ) {
        SpringApplication.run(SupplierApp1.class, args);
    }
}
