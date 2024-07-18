package com.yangyag.msa.keycloak;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class KeycloakSidecarApplication {
    public static void main(String[] args) {
        SpringApplication.run(KeycloakSidecarApplication.class, args);
    }
}