package io.github.nuto17.financetracker;

import io.github.nuto17.financetracker.config.properties.UrlBasedCorsConfigurationProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(UrlBasedCorsConfigurationProperties.class)
public class FinanceTrackerApplication {
    public static void main(String[] args) {
        SpringApplication.run(FinanceTrackerApplication.class, args);
    }
}
