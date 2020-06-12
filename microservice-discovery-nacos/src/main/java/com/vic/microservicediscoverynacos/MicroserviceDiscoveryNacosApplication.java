package com.vic.microservicediscoverynacos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MicroserviceDiscoveryNacosApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroserviceDiscoveryNacosApplication.class, args);
    }

}