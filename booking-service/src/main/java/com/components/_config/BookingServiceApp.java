package com.components._config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

@EnableJpaRepositories("com.components.repositories")
@SpringBootApplication(scanBasePackages = "com.components.*")
@EnableEurekaClient
@EntityScan(basePackages = "com.components.*")
public class BookingServiceApp {
    public static void main( String[] args ) {
        SpringApplication.run(BookingServiceApp.class, args);
    }
}

@Configuration
class RestTemplateConfig {

    // Create a bean for restTemplate to call services
    @Bean
    @LoadBalanced        // Load balance between service instances running at different ports.
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}