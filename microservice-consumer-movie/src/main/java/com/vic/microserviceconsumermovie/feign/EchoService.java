package com.vic.microserviceconsumermovie.feign;

import com.vic.microserviceconsumermovie.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "microservice-provider-user",
        fallback = EchoServiceFallback.class,
        configuration = EchoFeignConfiguration.class)
public interface EchoService {
    @GetMapping(value = "/{id}")
    User findById(@PathVariable("id")  Long id);
}

class EchoFeignConfiguration {
    @Bean
    public EchoServiceFallback echoServiceFallback() {
        return new EchoServiceFallback();
    }
}

class EchoServiceFallback implements EchoService {
    @Override
    public User findById(@PathVariable("id") Long id) {
        return new User();
    }
}