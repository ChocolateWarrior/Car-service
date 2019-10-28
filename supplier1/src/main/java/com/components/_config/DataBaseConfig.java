package com.components._config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = "com.components.*")
@EnableJpaRepositories(basePackages = "com.components.*")
public class DataBaseConfig {
}
