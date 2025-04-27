package com.example.financetracker;

import com.example.financetracker.config.propert.UrlBasedCorsConfigurationProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@EnableConfigurationProperties(UrlBasedCorsConfigurationProperties.class)
public class FinancetrackerApplication {
    public static void main(String[] args) {
        SpringApplication.run(FinancetrackerApplication.class, args);
    }
}
