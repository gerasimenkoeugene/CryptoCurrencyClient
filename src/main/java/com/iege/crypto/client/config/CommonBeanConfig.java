package com.iege.crypto.client.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.RestTemplate;


@Configuration
public class CommonBeanConfig {

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Bean
    public BCryptPasswordEncoder strongEncryptor(){
        return new BCryptPasswordEncoder();
    }
}
