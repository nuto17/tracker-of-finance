package com.example.financetracker.config.propert;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.cors.CorsConfiguration;

import java.util.LinkedHashMap;
import java.util.Map;

@ConfigurationProperties(prefix = "spring.cors.url")
@Getter
@Setter
public class UrlBasedCorsConfigurationProperties {
    private Map<String, CorsConfiguration> configurations = new LinkedHashMap<>();
}
