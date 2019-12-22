package com.components._config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication(scanBasePackages = "com.components.*")
@EnableCaching
@EntityScan(basePackages = "com.components.*")
//@EnableEurekaClient
public class MainApp {
    public static void main( String[] args ) {
        SpringApplication.run(MainApp.class, args);
    }
}

//
//@Configuration
//class RestTemplateConfig {
//
//    // Create a bean for restTemplate to call services
//    @Bean
//    @LoadBalanced        // Load balance between service instances running at different ports.
//    public RestTemplate restTemplate() {
//        return new RestTemplate();
//    }
//}