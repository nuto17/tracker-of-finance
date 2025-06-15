package io.github.nuto17.financetracker.config;

import io.github.nuto17.financetracker.config.propert.UrlBasedCorsConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class WebConfig {

    @Bean
    CorsFilter corsFilter(UrlBasedCorsConfigurationProperties properties) {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        properties.getConfigurations().forEach(source::registerCorsConfiguration);
        return new CorsFilter(source);
    }
}
