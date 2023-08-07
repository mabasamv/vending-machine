package com.vincent.assessment.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class VendingMachineConfig {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
