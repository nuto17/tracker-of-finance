package com.example.financetracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class FinancetrackerApplication {
    public static void main(String[] args) {
        SpringApplication.run(FinancetrackerApplication.class, args);
    }
}
