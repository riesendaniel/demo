package com.example.demo.config;

import org.springframework.boot.actuate.web.exchanges.InMemoryHttpExchangeRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public InMemoryHttpExchangeRepository createTraceRepository() {
        return new InMemoryHttpExchangeRepository();
    }

}
