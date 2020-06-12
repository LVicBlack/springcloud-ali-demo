package com.vic.microservicenacosprovider;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient
public class MicroserviceNacosProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroserviceNacosProviderApplication.class, args);
    }

    @RestController
    @RefreshScope
    class EchoController {
        @Value("${address}")
        private String address;

        @GetMapping("/print/address")
        public String printAddress() {
            return address;
        }

        @GetMapping("/echo/{string}")
        public String echo(@PathVariable String string) {
            return "Hello Nacos Discovery " + string;
        }

    }
}
